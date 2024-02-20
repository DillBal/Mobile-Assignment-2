package edu.oregonstate.cs492.assignment2.data


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherSearchResults(
    val items: List<WeatherItemClass>
)
