package com.eslamwaheed.domain.entity.realtime

import com.google.gson.annotations.SerializedName
data class RealtimeResponse(
    @SerializedName("location")
    val location: Location,
    @SerializedName("current")
    val current: Current
)