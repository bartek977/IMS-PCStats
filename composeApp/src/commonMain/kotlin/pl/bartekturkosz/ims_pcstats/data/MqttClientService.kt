package pl.bartekturkosz.ims_pcstats.data

import pl.bartekturkosz.ims_pcstats.data.model.SensorDataEntity

interface MqttClientService {
    suspend fun checkCredentials(url: String, login: String, password: String): Boolean

    suspend fun getSensorsValues(onUpdate: (SensorDataEntity) -> Unit)

    fun stopGettingSensorsValues()
}