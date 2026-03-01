package pl.bartekturkosz.ims_pcstats.data.model

data class SensorDataEntity(
    val sensor: String,
    val value: String,
    val timestamp: Long
)