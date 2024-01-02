package com.eslamwaheed.domain.entity.realtime


data class Current(
    val lastUpdatedEpoch: Int,
    val lastUpdated: String,
    val tempC: Double,
    val tempF: Double,
    val isDay: Int,
    val condition: Condition,
    val windMph: Double,
    val windKph: Double,
    val windDegree: Int,
    val windDir: String,
    val pressureMb: Int,
    val pressureIn: Double,
    val precipMm: Int,
    val precipIn: Int,
    val humidity: Int,
    val cloud: Int,
    val feelslikeC: Double,
    val feelslikeF: Double,
    val visKm: Int,
    val visMiles: Int,
    val uv: Int,
    val gustMph: Double,
    val gustKph: Int
)