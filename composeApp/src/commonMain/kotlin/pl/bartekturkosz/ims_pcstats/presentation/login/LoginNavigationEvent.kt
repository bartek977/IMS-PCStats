package pl.bartekturkosz.ims_pcstats.presentation.login

sealed class LoginNavigationEvent {
    data object LoginSuccess : LoginNavigationEvent()
    data object LoginFailed : LoginNavigationEvent()
}