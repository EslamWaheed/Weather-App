package com.eslamwaheed.domain.entity

import kotlinx.serialization.SerialName

data class BaseResponse(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String
)