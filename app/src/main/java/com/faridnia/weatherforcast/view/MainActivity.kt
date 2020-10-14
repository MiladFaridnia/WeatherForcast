package com.faridnia.weatherforcast.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faridnia.weatherforcast.R
import com.faridnia.weatherforcast.R.drawable.*
import com.faridnia.weatherforcast.model.DayTimes
import com.faridnia.weatherforcast.model.onecallresponse.Daily
import com.faridnia.weatherforcast.model.onecallresponse.Hourly
import com.faridnia.weatherforcast.model.onecallresponse.WeatherInfo
import com.faridnia.weatherforcast.util.Utils
import com.faridnia.weatherforcast.viewmodel.ForecastResultViewModel
import com.faridnia.weatherforcast.viewmodel.ForecastResultViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.city_temp_info_layout.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private var selectedCityName: String = "Tehran"
    private lateinit var weatherAdapter: WeatherForecastAdapter

    @Inject
    lateinit var factory: ForecastResultViewModelFactory

    private lateinit var viewModel: ForecastResultViewModel

    private val defaultCityLat = 51.421509
    private val defaultCityLng = 35.694389

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initViewModel()

        getCityList()

        getWeatherInfo(defaultCityLat, defaultCityLng)

        observeCityData()

        observeWeatherData()

        setCityClickListener()

    }

    private fun setCityClickListener() {
        cityNameTextView.setOnClickListener {
            showCityBottomSheet()
        }
    }
    private val cityListAdapter = CityListAdapter(cityList = emptyList())

    private fun showCityBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this@MainActivity)
        val dialogSheetView: View = layoutInflater.inflate(R.layout.cities_bottomsheet, null)

        bottomSheetDialog.apply {
            setContentView(dialogSheetView)
            show()
            setCancelable(true)
            setCanceledOnTouchOutside(true)
            behavior.peekHeight = 700
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            bottomSheetDialog.dismissWithAnimation = true
        }

        val citiesRecyclerView =
            dialogSheetView.findViewById<View>(R.id.citiesRecyclerView) as RecyclerView
        citiesRecyclerView.layoutManager = LinearLayoutManager(this)
        citiesRecyclerView.adapter = cityListAdapter

        cityListAdapter.onItemClick = { city ->
            getWeatherInfo(city.coord.lat , city.coord.lon)
            selectedCityName = city.name
            bottomSheetDialog.dismiss()
        }
    }

    private fun getCityList() {
        viewModel.getCityList()
    }

    private fun getWeatherInfo(longitude: Double, latitude: Double) {
        viewModel.getWeatherInfo(latitude, longitude)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, factory).get(ForecastResultViewModel::class.java)
    }

    private fun observeCityData() {
        viewModel.cityListLiveData.observe(this, Observer {
            Log.d("Milad", "Cities: $it")
            cityListAdapter.update(it)
        })
    }

    private fun observeWeatherData() {
        viewModel.weatherInfoLiveData.observe(this, Observer { weatherInfo ->
            Log.d("Milad", "result : " + weatherInfo.current)

            setMainBackground(weatherInfo.current.dt)

            showTemperatureInfo(
                weatherInfo.current.temp,
                getMinMaxTemperature(weatherInfo),
                selectedCityName,
                weatherInfo.current.weather[0].main,
                Utils().getDayTime(weatherInfo.current.dt)
            )

            showNextHours(weatherInfo.hourly)

            showNextDays(weatherInfo.daily)
        })
    }

    private fun showNextDays(daily: List<Daily>) {
        val dailyList = daily.subList(1, daily.size)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        weatherAdapter = WeatherForecastAdapter(dailyList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = weatherAdapter
        weatherAdapter.notifyDataSetChanged()

        weatherAdapter.onItemClick = { daily ->
            Log.d("Milad", daily.toString())
        }
    }

    private fun getMinMaxTemperature(weatherInfo: WeatherInfo) =
        Utils().getTemperature(weatherInfo.daily[0].temp.min) + "/"+
                Utils().getTemperature(weatherInfo.daily[0].temp.max)

    private fun showNextHours(hours: List<Hourly>) {
        var i = 0
        for (hour in hours) {
            if (i <  7) {
                val layout = NextHourWeatherLayout(this)
                layout.setNextHourData(hour)
                layoutWeatherNextHours.addView(layout)
                i += 1
            }
        }
    }

    private fun setMainBackground(dt: Long) {
        when (Utils().getDayTime(dt)) {
            DayTimes.Dawn -> {
                mainActivityContainer.background = getDrawable(dawn_background)
                window.statusBarColor = resources.getColor(R.color.dawnTopGradientColor)
            }
            DayTimes.Morning -> {
                mainActivityContainer.background = getDrawable(morning_background)
                window.statusBarColor = resources.getColor(R.color.morningTopGradientColor)
            }
            DayTimes.Noon -> {
                mainActivityContainer.background = getDrawable(noon_background)
                window.statusBarColor = resources.getColor(R.color.noonTopGradientColor)
            }
            DayTimes.Evening -> {
                mainActivityContainer.background = getDrawable(evening_background)
                window.statusBarColor = resources.getColor(R.color.eveningTopGradientColor)
            }
            DayTimes.Night -> {
                mainActivityContainer.background = getDrawable(night_background)
                window.statusBarColor = resources.getColor(R.color.nightTopGradientColor)
            }
            else -> {
                mainActivityContainer.background = getDrawable(morning_background)
                window.statusBarColor = resources.getColor(R.color.morningTopGradientColor)
            }
        }
    }

    private fun showTemperatureInfo(
        cityTemperature: Double,
        cityMinMaxTemperature: String,
        cityName: String,
        cityWeather: String,
        dayTime: DayTimes
    ) {
        cityNameTextView.text = cityName
        cityTemperatureTextView.text = Utils().getTemperature(cityTemperature)
        cityMinMaxTempTextView.text = cityMinMaxTemperature.toString()
        cityWeatherImageView.setImageResource(Utils().getRelatedWeatherImage(cityWeather,dayTime))
    }
}
