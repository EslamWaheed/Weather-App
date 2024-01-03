package com.eslamwaheed.weatherapp.presentation.search.viewmodel

import com.eslamwaheed.domain.entity.search.SearchResponseItem

sealed class SearchSideEffect {
    data class ShowError(val message: String?) : SearchSideEffect()
    data class NavigateBackToHome(val item: SearchResponseItem) : SearchSideEffect()
}
