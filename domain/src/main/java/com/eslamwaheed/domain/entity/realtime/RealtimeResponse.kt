package com.eslamwaheed.domain.entity.realtime

import kotlinx.serialization.SerialName

data class RealtimeResponse(
    @SerialName("location")
    val location: Location,
    @SerialName("current")
    val current: Current
)