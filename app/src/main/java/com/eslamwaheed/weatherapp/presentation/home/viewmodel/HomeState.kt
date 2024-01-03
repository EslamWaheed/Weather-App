package com.eslamwaheed.weatherapp.presentation.home.viewmodel

import com.eslamwaheed.domain.entity.realtime.RealtimeResponse

data class HomeState(
    val isLoading: Boolean = false,
    val realtimeResponse: RealtimeResponse? = null
)