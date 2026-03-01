package pl.bartekturkosz.ims_pcstats.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import pl.bartekturkosz.ims_pcstats.data.MqttClientService
import pl.bartekturkosz.ims_pcstats.data.datastore.ApplicationPreferences
import pl.bartekturkosz.ims_pcstats.data.model.UserCredentials

class LoginViewModel(
    private val mqttClientService: MqttClientService,
    private val applicationPreferences: ApplicationPreferences
) : ViewModel() {
    val url = MutableStateFlow("")
    val login = MutableStateFlow("")
    val password = MutableStateFlow("")
    val navigation = MutableSharedFlow<LoginNavigationEvent>()

    fun onLoginClick() {
        viewModelScope.launch {
            runCatching { mqttClientService.checkCredentials(url.value, login.value, password.value) }
                .onSuccess { success ->
                    if (success) {
                        applicationPreferences.saveCredentials(
                            UserCredentials(
                                login = login.value,
                                password = password.value,
                                url = url.value
                            )
                        )
                        navigation.emit(LoginNavigationEvent.NavigateToDashboard)
                    } else {
                        navigation.emit(LoginNavigationEvent.LoginFailed)
                    }
                }
                .onFailure {
                    navigation.emit(LoginNavigationEvent.LoginFailed)
                }
        }
    }
}