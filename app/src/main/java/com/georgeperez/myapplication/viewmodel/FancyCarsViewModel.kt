package com.georgeperez.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.georgeperez.myapplication.model.CarResponse
import com.georgeperez.myapplication.model.FancyCarRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import kotlin.collections.HashMap

object FancyCarsViewModel {
    val cars = MutableLiveData<CarResponse>()
    val availability = MutableLiveData<HashMap<CarResponse.Car, String>>()

    var availabilityData = mapOf(
        CarResponse.Car(1, "", "Fast Car", "Dodge", "Viper", 1990) to "In Dealership",
        CarResponse.Car(2, "", "Red Car", "Toyota", "Camry", 1992) to "Out of Stock",
        CarResponse.Car(3, "", "Blue Car", "Ford", "F150", 2000) to "Unavailable",
        CarResponse.Car(4, "", "Green Car", "GM", "Laughter", 1990) to "Unavailable",
        CarResponse.Car(5, "", "Yellow Car", "Honda", "Civic", 2020) to "Out of Stock",
        CarResponse.Car(6, "", "Gold Car", "Chrysler", "Pacifica", 1995) to "In Dealership",
        CarResponse.Car(7, "", "Silver Car", "Jeep", "Cherokee", 1990) to "In Dealership",
        CarResponse.Car(8, "", "Shiny Car", "Hummer", "Viper", 2006) to "Out of Stock",
        CarResponse.Car(9, "", "Black Car", "Dodge", "Caravan", 2005) to "Unavailable",
        CarResponse.Car(10, "", "White Car", "Nissan", "Express", 1990) to "In Dealership"
    )

    fun getCar() {
        FancyCarRepository.getCar()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                cars.value = it
                it.list?.forEach { car ->
                    getAvailability(car)
                }
            }, {
                Log.d("TAG_X", it.localizedMessage ?: "getCar Error")
            })
    }

    private fun getAvailability(car: CarResponse.Car) {
        FancyCarRepository.getAvailability(car.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                availability.value?.put(car, it.available.trim())
            }, {
                Log.d("TAG_X", it.localizedMessage ?: "getAvailability Error")
            })
    }

    fun sortByName(){
        availabilityData = availabilityData.toSortedMap(compareBy { it.name })
    }
    fun sortByAvailability(){
        availabilityData = availabilityData.toList().sortedBy {
            (k,v) -> v
        }.toMap()
    }
}