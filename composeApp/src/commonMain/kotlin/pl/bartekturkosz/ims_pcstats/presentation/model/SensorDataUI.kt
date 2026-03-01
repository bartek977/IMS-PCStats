package pl.bartekturkosz.ims_pcstats.presentation.model

import org.jetbrains.compose.resources.DrawableResource

data class SensorDataUI(
    val name: String,
    val value: String,
    val lastUpdate: String,
    val icon: DrawableResource
)
