package com.georgeperez.myapplication.model

import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object FancyRetrofit {
    private var carAPI: CarAPI
    private var client = OkHttpClient.Builder().build()

    init {
        carAPI = createCarAPI(createRetrofit())
    }

    private fun createRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.fancycar.ca/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(client)
        .build()

    private fun createCarAPI(retrofit: Retrofit): CarAPI {
        return retrofit.create(CarAPI::class.java)
    }

    fun getCarResponse(): Observable<CarResponse> {
        return carAPI.getCarResponse()
    }

    fun getAvailabilityResponse(id: Int): Observable<AvailabilityResponse> {
        return carAPI.getAvailabilityResponse(id)
    }
}