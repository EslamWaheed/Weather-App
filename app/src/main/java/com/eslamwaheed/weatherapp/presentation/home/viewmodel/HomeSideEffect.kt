package com.eslamwaheed.weatherapp.presentation.home.viewmodel

sealed class HomeSideEffect {
    data object PermissionGranted : HomeSideEffect()
    data object PermissionDenied : HomeSideEffect()
    data object NavigateToSearch : HomeSideEffect()
    data class ShowError(val message: String?) : HomeSideEffect()
}
