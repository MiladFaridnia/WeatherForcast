package com.faridnia.weatherforcast.network

interface RequestCompleteListener<T> {

    fun onRequestSuccess(data: T)

    fun onRequestFailed(errorMessage: String)
}