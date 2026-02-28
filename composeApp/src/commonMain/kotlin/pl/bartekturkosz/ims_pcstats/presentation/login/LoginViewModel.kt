package pl.bartekturkosz.ims_pcstats.presentation.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel : ViewModel() {
    val url = MutableStateFlow("")
    val login = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun onLoginClick() {
        // TODO to be implemented
    }
}