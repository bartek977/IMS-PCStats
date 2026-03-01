package pl.bartekturkosz.ims_pcstats.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import pl.bartekturkosz.ims_pcstats.data.MqttClientService
import pl.bartekturkosz.ims_pcstats.data.database.Database
import pl.bartekturkosz.ims_pcstats.data.model.SensorDataEntity
import pl.bartekturkosz.ims_pcstats.presentation.model.SensorDataUI
import pl.bartekturkosz.ims_pcstats.presentation.model.toUI

class DashboardViewModel(
    private val mqttClientService: MqttClientService,
    private val database: Database
) : ViewModel() {

    val items = MutableStateFlow<List<SensorDataUI>>(listOf())

    init {
        loadData()
        listenToSensorData()
    }

    private fun loadData() {
        viewModelScope.launch {
            database.sensorDataDao.getAllAsFlow().collect {
                items.value = it.map { entity -> entity.toUI() }
            }
        }
    }

    private fun listenToSensorData() {
        viewModelScope.launch {
            mqttClientService.getSensorsValues { saveData(it) }
        }
    }

    private fun saveData(data: SensorDataEntity) {
        viewModelScope.launch {
            database.sensorDataDao.insert(data)
        }
    }

    override fun onCleared() {
        mqttClientService.stopGettingSensorsValues()
        super.onCleared()
    }
}