package com.eslamwaheed.weatherapp.di

import com.eslamwaheed.data.remote.WeatherApiServices
import com.eslamwaheed.data.repo.WeatherRepoImpl
import com.eslamwaheed.domain.repo.WeatherRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun providesWeatherRepo(apiServices: WeatherApiServices): WeatherRepo {
        return WeatherRepoImpl(apiServices)
    }

}