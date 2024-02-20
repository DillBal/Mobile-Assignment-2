package edu.oregonstate.cs492.assignment2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import edu.oregonstate.cs492.assignment2.data.ForecastPeriod
import edu.oregonstate.cs492.assignment2.data.WeatherItemClass
import edu.oregonstate.cs492.assignment2.data.WeatherService
import java.util.Calendar
import java.util.Locale


class ForecastAdapter() :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {
    private var weatherList = listOf<WeatherItemClass>()
    override fun getItemCount() = weatherList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_list_item, parent, false)
        return ViewHolder(view)
    }

    fun updateWeatherList(newWeatherList: List<WeatherItemClass>?) {
        notifyItemRangeRemoved(0, weatherList.size)
        weatherList = newWeatherList ?: listOf()
        notifyItemRangeInserted(0, weatherList.size)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val monthTV: TextView = view.findViewById(R.id.tv_month)
        private val dayTV: TextView = view.findViewById(R.id.tv_day)
        private val highTempTV: TextView = view.findViewById(R.id.tv_high_temp)
        private val lowTempTV: TextView = view.findViewById(R.id.tv_low_temp)
        private val shortDescTV: TextView = view.findViewById(R.id.tv_short_description)
        private val popTV: TextView = view.findViewById(R.id.tv_pop)


        fun bind(weatherItemClass: WeatherItemClass) {
            val firstWeatherData = weatherItemClass.list.firstOrNull()

            monthTV.text = getMonthFromDate(firstWeatherData?.dt ?: 0)
            dayTV.text = getDayFromDate(firstWeatherData?.dt ?: 0)
            highTempTV.text = firstWeatherData?.main?.tempMax?.toString() ?: ""
            lowTempTV.text = firstWeatherData?.main?.tempMin?.toString() ?: ""
            shortDescTV.text = firstWeatherData?.weather?.firstOrNull()?.description ?: ""
            popTV.text = firstWeatherData?.pop.toString()

            itemView.setOnLongClickListener {
                Snackbar.make(
                    itemView,
                    firstWeatherData?.weather?.firstOrNull()?.description ?: "",
                    Snackbar.LENGTH_LONG
                ).show()
                true
            }
        }

        // Helper functions to extract month and day from timestamp
        private fun getMonthFromDate(timestamp: Long): String {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timestamp * 1000
            return (calendar.get(Calendar.MONTH) + 1).toString()
        }

        private fun getDayFromDate(timestamp: Long): String {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timestamp * 1000
            return calendar.get(Calendar.DAY_OF_MONTH).toString()
        }
    }
}