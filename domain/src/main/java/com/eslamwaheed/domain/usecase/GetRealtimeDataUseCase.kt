package com.eslamwaheed.domain.usecase

import com.eslamwaheed.domain.repo.WeatherRepo
import javax.inject.Inject

class GetRealtimeDataUseCase @Inject constructor(private val repos: WeatherRepo) {
    suspend operator fun invoke(q: String) = repos.getRealtimeData(q)
}