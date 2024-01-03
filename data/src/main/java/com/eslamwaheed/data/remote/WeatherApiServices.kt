package com.eslamwaheed.data.remote

import com.eslamwaheed.domain.entity.realtime.RealtimeResponse
import com.eslamwaheed.domain.entity.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiServices {
    @GET("current.json")
    suspend fun getRealtimeData(@Query("q") q: String): Result<RealtimeResponse>

    @GET("search.json")
    suspend fun getSearchData(@Query("q") q: String): Result<SearchResponse>
}