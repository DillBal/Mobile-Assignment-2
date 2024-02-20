package edu.oregonstate.cs492.assignment2.data
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherItemClass(
    @Json(name = "cod") val cod: String,
    @Json(name = "message") val message: Int,
    @Json(name = "cnt") val county: Int,
    @Json(name = "list") val list: List<WeatherData>,
    @Json(name = "city") val city: City
) {
    @JsonClass(generateAdapter = true)
    data class WeatherData(
        @Json(name = "dt") val dt: Long,
        @Json(name = "main") val main: Main,
        @Json(name = "weather") val weather: List<Weather>,
        @Json(name = "pop") val pop: Double,
        @Json(name = "dt_txt") val dtTxt: String
    ) {
        @JsonClass(generateAdapter = true)
        data class Main(
            @Json(name = "temp_max") val tempMax: Double,
            @Json(name = "temp_min") val tempMin: Double
        )

        @JsonClass(generateAdapter = true)
        data class Weather(
            @Json(name = "description") val description: String
        )
    }

    @JsonClass(generateAdapter = true)
    data class City(
        @Json(name = "name") val name: String
    )
}
