package com.eslamwaheed.data.repo

import com.eslamwaheed.data.remote.WeatherApiServices
import com.eslamwaheed.domain.entity.realtime.RealtimeResponse
import com.eslamwaheed.domain.entity.search.SearchResponse
import com.eslamwaheed.domain.repo.WeatherRepo
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(private val apiServices: WeatherApiServices) :
    WeatherRepo {
    override suspend fun getRealtimeData(q: String): Result<RealtimeResponse> {
        return apiServices.getRealtimeData(q)
    }

    override suspend fun getSearchData(q: String): Result<SearchResponse> {
        return apiServices.getSearchData(q)
    }
}