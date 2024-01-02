package com.eslamwaheed.domain.repo

import com.eslamwaheed.domain.entity.realtime.RealtimeResponse
import com.eslamwaheed.domain.entity.search.SearchResponse

interface WeatherRepo {
    suspend fun getRealtimeData(q:String): RealtimeResponse

    suspend fun getSearchData(): SearchResponse
}