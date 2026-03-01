package pl.bartekturkosz.ims_pcstats.data

import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.MqttGlobalPublishFilter
import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import pl.bartekturkosz.ims_pcstats.data.datastore.ApplicationPreferences
import pl.bartekturkosz.ims_pcstats.data.model.SensorDataEntity
import java.util.UUID
import kotlin.coroutines.resume
import kotlin.time.Clock

class AndroidMqttClientService(
    private val applicationPreferences: ApplicationPreferences
) : MqttClientService {

    var client: Mqtt5AsyncClient? = null

    override suspend fun checkCredentials(
        url: String,
        login: String,
        password: String
    ): Boolean = suspendCancellableCoroutine { cont ->
        try {
            initClient(url)
            connectClient(
                login = login,
                password = password,
                onComplete = { error ->
                    client?.disconnect()
                    cont.resume(error == null)
                }
            )
        } catch (e: Exception) {
            cont.resume(false)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun getSensorsValues(onUpdate: (SensorDataEntity) -> Unit) {
        stopGettingSensorsValues()
        val credentials = applicationPreferences.getCredentials() ?: throw Exception("No credentials found")
        subscribeAndListen(
            url = credentials.url,
            login = credentials.login,
            password = credentials.password,
            onUpdate = onUpdate
        )
    }

    override fun stopGettingSensorsValues() {
        client?.disconnect()
    }

    private fun subscribeAndListen(
        url: String,
        login: String,
        password: String,
        onUpdate: (SensorDataEntity) -> Unit
    ) {
        if (client == null) {
            initClient(url)
        }
        connectClient(
            login = login,
            password = password,
            onComplete = { error ->
                if (error == null) {
                    client!!.subscribeWith()
                        .topicFilter("#")
                        .qos(MqttQos.EXACTLY_ONCE)
                        .send()
                        .whenComplete { _, subThrowable ->
                            if (subThrowable == null) {
                                client!!.publishes(MqttGlobalPublishFilter.ALL) { publish: Mqtt5Publish ->
                                    val value = publish.payloadAsBytes.toString(Charsets.UTF_8)
                                    onUpdate(
                                        SensorDataEntity(
                                            sensor = publish.topic.toString(),
                                            value = value,
                                            timestamp = Clock.System.now().toEpochMilliseconds()
                                        )
                                    )
                                }
                            }
                        }
                }
            }
        )
    }

    private fun initClient(url: String) {
        val builder = MqttClient.builder()
            .identifier(UUID.randomUUID().toString())
            .serverHost(url)
            .serverPort(8883)
            .useMqttVersion5()
            .sslWithDefaultConfig()
        client = builder.buildAsync()
    }

    private fun connectClient(
        login: String,
        password: String,
        onComplete: (error: Throwable?) -> Unit
    ) {
        client!!.connectWith()
            .simpleAuth()
            .username(login)
            .password(password.toByteArray())
            .applySimpleAuth()
            .send()
            .whenComplete { _, throwable -> onComplete(throwable) }
    }
}