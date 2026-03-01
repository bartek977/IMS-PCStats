package pl.bartekturkosz.ims_pcstats.data

import interop.SwiftMqttClient
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine
import pl.bartekturkosz.ims_pcstats.data.datastore.ApplicationPreferences
import pl.bartekturkosz.ims_pcstats.data.model.SensorDataEntity
import kotlin.coroutines.resume
import kotlin.time.Clock

@OptIn(ExperimentalForeignApi::class)
class IosMqttClientService(
    private val applicationPreferences: ApplicationPreferences
) : MqttClientService {
    var client: SwiftMqttClient? = null

    override suspend fun checkCredentials(
        url: String,
        login: String,
        password: String
    ): Boolean {
        client = SwiftMqttClient()
        return suspendCancellableCoroutine { cont ->
            runCatching {
                client?.connectWithHost(
                    host = url,
                    port = 8883u,
                    username = login,
                    password = password,
                    completion = { success: Boolean ->
                        client?.disconnect()
                        cont.resume(success)
                    },
                    useTLS = true,
                    connectTimeoutSeconds = 10
                )
            }
                .onFailure {
                    cont.resume(false)
                }

        }
    }

    override suspend fun getSensorsValues(onUpdate: (SensorDataEntity) -> Unit){
        if (client == null) {
            client = SwiftMqttClient()
        }
        val credentials = applicationPreferences.getCredentials() ?: throw Exception("No credentials found")
            client?.connectWithHost(
                host = credentials.url,
                port = 8883u,
                username = credentials.login,
                password = credentials.password,
                completion = { success: Boolean ->
                    if (success) {
                        client?.subscribeWithTopic("#") { sensor, value ->
                            if (sensor != null && value != null) {
                                val entity = SensorDataEntity(
                                    sensor = sensor,
                                    value = value,
                                    timestamp = Clock.System.now().toEpochMilliseconds()
                                )
                                onUpdate(entity)
                            }
                        }
                    }
                },
                useTLS = true,
                connectTimeoutSeconds = 10
            )
    }

    override fun stopGettingSensorsValues() {
        client?.disconnect()
    }
}