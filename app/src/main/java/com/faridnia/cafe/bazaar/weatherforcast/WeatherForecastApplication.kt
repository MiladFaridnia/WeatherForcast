package com.faridnia.cafe.bazaar.weatherforcast


import com.faridnia.cafe.bazaar.weatherforcast.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class WeatherForecastApplication : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
            .application(this)
            .build()
    }
}