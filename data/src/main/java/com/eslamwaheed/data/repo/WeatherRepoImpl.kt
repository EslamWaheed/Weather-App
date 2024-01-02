package com.eslamwaheed.data.repo

import com.eslamwaheed.data.remote.WeatherApiServices
import com.eslamwaheed.domain.entity.realtime.RealtimeResponse
import com.eslamwaheed.domain.entity.search.SearchResponse
import com.eslamwaheed.domain.repo.WeatherRepo
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(private val apiServices: WeatherApiServices) :
    WeatherRepo {
    override suspend fun getRealtimeData(q: String): RealtimeResponse {
        return apiServices.getRealtimeData(q)
    }

    override suspend fun getSearchData(): SearchResponse {
        TODO("Not yet implemented")
    }
}