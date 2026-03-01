package pl.bartekturkosz.ims_pcstats.presentation.model

import imspcstats.composeapp.generated.resources.Res
import imspcstats.composeapp.generated.resources.ic_temperature
import org.jetbrains.compose.resources.DrawableResource
import pl.bartekturkosz.ims_pcstats.data.model.SensorDataEntity

data class SensorDataUI(
    val name: String,
    val value: String,
    val lastUpdate: String,
    val icon: DrawableResource
)

// TODO to be implemented properly later
fun SensorDataEntity.toUI() = SensorDataUI(
    name = sensor,
    value = value,
    lastUpdate = timestamp.toString(),
    icon = Res.drawable.ic_temperature
)