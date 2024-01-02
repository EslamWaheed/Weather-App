package com.eslamwaheed.weatherapp.di

import com.eslamwaheed.domain.repo.WeatherRepo
import com.eslamwaheed.domain.usecase.GetRealtimeDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun providesRealtimeDataUseCase(repo: WeatherRepo): GetRealtimeDataUseCase {
        return GetRealtimeDataUseCase(repo)
    }
}