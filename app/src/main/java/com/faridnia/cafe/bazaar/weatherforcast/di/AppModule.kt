package com.faridnia.cafe.bazaar.weatherforcast.di

import android.app.Application
import android.content.Context
import com.faridnia.cafe.bazaar.weatherforcast.network.ApiInterface
import com.faridnia.cafe.bazaar.weatherforcast.network.RetrofitClient


import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    abstract fun provideContext(application: Application): Context

    companion object {

        @Provides
        @Singleton
        @JvmStatic
        fun provideApiService(): ApiInterface {
            return RetrofitClient.client.create(ApiInterface::class.java)
        }
    }
}