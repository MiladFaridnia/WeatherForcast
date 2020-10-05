package com.faridnia.cafe.bazaar.weatherforcast.network

interface RequestCompleteListener<T> {

    fun onRequestSuccess(data: T)

    fun onRequestFailed(errorMessage: String)
}