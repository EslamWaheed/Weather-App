package com.eslamwaheed.weatherapp.presentation.search.viewmodel

import com.eslamwaheed.domain.entity.search.SearchResponse

data class SearchState(
    val isLoading: Boolean = false,
    val searchResponse: SearchResponse? = null
)