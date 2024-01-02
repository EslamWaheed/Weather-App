package com.eslamwaheed.domain.entity.realtime

import kotlinx.serialization.SerialName

data class Location(
    @SerialName("name")
    val name: String,
    @SerialName("region")
    val region: String,
    @SerialName("country")
    val country: String,
    @SerialName("lat")
    val lat: Double,
    @SerialName("lon")
    val lon: Double,
    @SerialName("tz_id")
    val tzId: String,
    @SerialName("localtime_epoch")
    val localtimeEpoch: Int,
    @SerialName("localtime")
    val localtime: String
)