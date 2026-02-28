package pl.bartekturkosz.ims_pcstats.data

interface MqttClientService {
    suspend fun checkCredentials(url: String, login: String, password: String): Boolean
}