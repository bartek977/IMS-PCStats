package pl.bartekturkosz.ims_pcstats.data

import interop.SwiftMqttClient
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class IosMqttClientService : MqttClientService {

    @OptIn(ExperimentalForeignApi::class)
    override suspend fun checkCredentials(
        url: String,
        login: String,
        password: String
    ): Boolean {
        val client = SwiftMqttClient()
        return suspendCancellableCoroutine { cont ->
            runCatching {
                client.connectWithHost(
                    host = url,
                    port = 8883u,
                    username = login,
                    password = password,
                    completion = { success: Boolean ->
                        client.disconnect()
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
}