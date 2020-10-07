package com.faridnia.cafe.bazaar.weatherforcast.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faridnia.cafe.bazaar.weatherforcast.viewmodel.ForecastResultModel
import com.faridnia.cafe.bazaar.weatherforcast.viewmodel.ForecastResultModelImpl
import com.faridnia.cafe.bazaar.weatherforcast.viewmodel.ForecastResultViewModel
import com.faridnia.cafe.bazaar.weatherforcast.viewmodel.ForecastResultViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun bindMainViewModel(viewModel: ForecastResultViewModel): ViewModel

    @Binds
    abstract fun bindModel(forecastResultModelImpl: ForecastResultModelImpl): ForecastResultModel

    @Binds
    abstract fun bindForecastResultViewModelFactory(factory: ForecastResultViewModelFactory): ViewModelProvider.Factory
}