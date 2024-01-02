package com.eslamwaheed.domain.entity.realtime


import kotlinx.serialization.SerialName


data class Condition(
    @SerialName("text")
    val text: String,
    @SerialName("icon")
    val icon: String,
    @SerialName("code")
    val code: Int
)