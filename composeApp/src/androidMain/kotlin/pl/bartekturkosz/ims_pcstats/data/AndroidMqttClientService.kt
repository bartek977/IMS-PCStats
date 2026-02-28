package pl.bartekturkosz.ims_pcstats.data

import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.UUID
import kotlin.coroutines.resume

class AndroidMqttClientService : MqttClientService {

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