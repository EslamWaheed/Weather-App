package com.eslamwaheed.weatherapp.di

import com.eslamwaheed.data.remote.WeatherApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter("key", "7aed033234c04c5da24135558240101")
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
            }.connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

//    @Provides
//    @Singleton
//    fun providesContentType() = "application/json".toMediaType()
//
//    @Provides
//    @Singleton
//    fun providesJson() = Json {
//        ignoreUnknownKeys = true
//        encodeDefaults = true
//    }
//
//    @Provides
//    @Singleton
//    fun providesJsonConverterFactory(json: Json, contentType: MediaType): Converter.Factory {
//        return json.asConverterFactory(contentType)
//    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesWeatherApiServices(retrofit: Retrofit): WeatherApiServices {
        return retrofit.create(WeatherApiServices::class.java)
    }
}

//private fun provideKotlinSerialization(): Converter.Factory {
//    val contentType = "application/json".toMediaType()
//    val json = Json {
//        ignoreUnknownKeys = true
//        encodeDefaults = true
//    }
//    return json.asConverterFactory(contentType)
//}