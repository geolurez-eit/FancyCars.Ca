package com.georgeperez.myapplication.model

import io.reactivex.rxjava3.core.Observable
import java.util.*

object FancyCarRepository {

    private val fancyRetrofit = FancyRetrofit

    fun getCar():Observable<CarResponse>{
        return fancyRetrofit.getCarResponse()
    }
    fun getAvailability(id:Int):Observable<AvailabilityResponse>{
        return fancyRetrofit.getAvailabilityResponse(id)
    }
}