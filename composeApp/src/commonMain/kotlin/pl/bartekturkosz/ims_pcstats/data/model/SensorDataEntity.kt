package pl.bartekturkosz.ims_pcstats.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "sensor_data", primaryKeys = ["sensor", "timestamp"])
data class SensorDataEntity(
    @ColumnInfo(name = "sensor")
    val sensor: String,

    @ColumnInfo(name = "value")
    val value: String,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long
)