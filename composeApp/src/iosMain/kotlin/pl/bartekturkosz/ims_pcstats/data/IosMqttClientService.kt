package pl.bartekturkosz.ims_pcstats.data

class IosMqttClientService : MqttClientService {

    override suspend fun checkCredentials(
        url: String,
        login: String,
        password: String
    ): Boolean {
        return false
    }
}