package pl.bartekturkosz.ims_pcstats.data

class AndroidMqttClientService : MqttClientService {

    override suspend fun checkCredentials(
        url: String,
        login: String,
        password: String
    ): Boolean {
        return false
    }
}