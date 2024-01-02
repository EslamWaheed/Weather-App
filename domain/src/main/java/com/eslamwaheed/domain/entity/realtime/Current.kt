package com.eslamwaheed.domain.entity.realtime

import kotlinx.serialization.SerialName

data class Current(
    @SerialName("last_updated_epoch")
    val lastUpdatedEpoch: Int,
    @SerialName("last_updated")
    val lastUpdated: String,
    @SerialName("temp_c")
    val tempC: Double,
    @SerialName("temp_f")
    val tempF: Double,
    @SerialName("is_day")
    val isDay: Int,
    @SerialName("condition")
    val condition: Condition,
    @SerialName("wind_mph")
    val windMph: Double,
    @SerialName("wind_kph")
    val windKph: Double,
    @SerialName("wind_degree")
    val windDegree: Int,
    @SerialName("wind_dir")
    val windDir: String,
    @SerialName("pressure_mb")
    val pressureMb: Int,
    @SerialName("pressure_in")
    val pressureIn: Double,
    @SerialName("precip_mm")
    val precipMm: Int,
    @SerialName("precip_in")
    val precipIn: Int,
    @SerialName("humidity")
    val humidity: Int,
    @SerialName("cloud")
    val cloud: Int,
    @SerialName("feelslike_c")
    val feelslikeC: Double,
    @SerialName("feelslike_f")
    val feelslikeF: Double,
    @SerialName("vis_km")
    val visKm: Int,
    @SerialName("vis_miles")
    val visMiles: Int,
    @SerialName("uv")
    val uv: Int,
    @SerialName("gust_mph")
    val gustMph: Double,
    @SerialName("gust_kph")
    val gustKph: Int
)