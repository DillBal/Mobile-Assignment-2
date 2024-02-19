package edu.oregonstate.cs492.assignment2.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherItemClass(
    val name: String,
    @Json(name = "weather count") val weather: Int


)

