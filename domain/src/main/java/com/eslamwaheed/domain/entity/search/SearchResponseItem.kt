package com.eslamwaheed.domain.entity.search

import kotlinx.serialization.SerialName

data class SearchResponseItem(
    @SerialName("id")
    val id: Int,
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
    @SerialName("url")
    val url: String
)