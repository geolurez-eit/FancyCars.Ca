package com.georgeperez.myapplication.model

import com.georgeperez.myapplication.util.Constants
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CarAPI {
    @GET(Constants.URL_PATH_CARS)
    fun getCarResponse(): Observable<List<Car>>

    @GET(Constants.URL_PATH_AVAILABILITY)
    fun getAvailabilityResponse(
        @Query("id") id:Int
    ): Observable<AvailabilityResponse>

}