package edu.oregonstate.cs492.assignment2.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/forecast")
    fun searchWeather(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") appId: String = "d443c1d7106611ceccfccbc1776b8ec2"
    ): Call<WeatherSearchResults>

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/"
        ///WeatherService.create()
        fun create(): WeatherService{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(WeatherService::class.java)
        }
    }
}