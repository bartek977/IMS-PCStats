package pl.bartekturkosz.ims_pcstats.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.bartekturkosz.ims_pcstats.data.MqttClientService
import pl.bartekturkosz.ims_pcstats.presentation.model.SensorDataUI
import pl.bartekturkosz.ims_pcstats.presentation.model.toUI

class DashboardViewModel(
    private val mqttClientService: MqttClientService
) : ViewModel() {

    val items = MutableStateFlow<List<SensorDataUI>>(listOf())

    init {
        listenToSensorData()
    }

    private fun listenToSensorData() {
        viewModelScope.launch {
            mqttClientService.getSensorsValues { newData ->
                items.update { it.plus(newData.toUI()) }
            }
        }
    }

    override fun onCleared() {
        mqttClientService.stopGettingSensorsValues()
        super.onCleared()
    }
}