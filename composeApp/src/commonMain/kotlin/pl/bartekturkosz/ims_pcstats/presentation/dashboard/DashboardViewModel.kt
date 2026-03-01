package pl.bartekturkosz.ims_pcstats.presentation.dashboard

import androidx.lifecycle.ViewModel
import imspcstats.composeapp.generated.resources.Res
import imspcstats.composeapp.generated.resources.ic_temperature
import kotlinx.coroutines.flow.MutableStateFlow
import pl.bartekturkosz.ims_pcstats.presentation.model.SensorDataUI

class DashboardViewModel : ViewModel() {

    val items = MutableStateFlow(listOf(
        SensorDataUI(
            name = "Temperature zewnętrzna",
            value = "15",
            lastUpdate = "10:30:00",
            icon = Res.drawable.ic_temperature
        ),
        SensorDataUI(
            name = "Temperatura CWU",
            value = "45",
            lastUpdate = "10:30:00",
            icon = Res.drawable.ic_temperature
        ),
    ))
}