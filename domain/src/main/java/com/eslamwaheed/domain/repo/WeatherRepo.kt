package com.eslamwaheed.domain.repo

import com.eslamwaheed.domain.entity.realtime.RealtimeResponse
import com.eslamwaheed.domain.entity.search.SearchResponse

interface WeatherRepo {
    suspend fun getRealtimeData(q: String): Result<RealtimeResponse>

    suspend fun getSearchData(q: String): Result<SearchResponse>
}