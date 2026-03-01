package pl.bartekturkosz.ims_pcstats.presentation.login

sealed class LoginNavigationEvent {
    data object NavigateToDashboard : LoginNavigationEvent()
    data object LoginFailed : LoginNavigationEvent()
}