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

    override fun getItemCount() = weatherList.size

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
        private lateinit var currentForecastPeriod: ForecastPeriod

        init {
            view.setOnLongClickListener {
                Snackbar.make(
                    view,
                    currentForecastPeriod.longDesc,
                    Snackbar.LENGTH_LONG
                ).show()
                true
            }
        }

        fun bind(forecastPeriod: WeatherItemClass) {
            currentForecastPeriod = weatherItemClass

            val cal = Calendar.getInstance()
            cal.set(forecastPeriod.year, forecastPeriod.month, forecastPeriod.day)

        }
    }
}