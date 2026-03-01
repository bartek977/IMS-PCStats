package pl.bartekturkosz.ims_pcstats.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import pl.bartekturkosz.ims_pcstats.data.MqttClientService

class LoginViewModel(
    private val mqttClientService: MqttClientService
) : ViewModel() {
    val url = MutableStateFlow("")
    val login = MutableStateFlow("")
    val password = MutableStateFlow("")
    val navigation = MutableSharedFlow<LoginNavigationEvent>()

    fun onLoginClick() {
        viewModelScope.launch {
            val success = mqttClientService.checkCredentials(url.value, login.value, password.value)
            if (success) {
                navigation.emit(LoginNavigationEvent.NavigateToDashboard)
            } else {
                navigation.emit(LoginNavigationEvent.LoginFailed)
            }
        }
    }
}