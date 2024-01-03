package com.eslamwaheed.weatherapp.presentation.search.viewmodel

sealed class SearchSideEffect {
    data class ShowError(val message: String?) : SearchSideEffect()
}
