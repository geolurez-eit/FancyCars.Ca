package com.georgeperez.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.georgeperez.myapplication.model.Car
import com.georgeperez.myapplication.model.FancyCarRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object FancyCarsViewModel {
    private val cars = MutableLiveData<List<Car>>()
    private val availability = MutableLiveData<HashMap<Car, String>>()

    var allCarData = mapOf(
        Car(1, "", "Fast Car", "Dodge", "Viper", 1990) to "In Dealership",
        Car(2, "", "Red Car", "Toyota", "Camry", 1992) to "Out of Stock",
        Car(3, "", "Blue Car", "Ford", "F150", 2000) to "Unavailable",
        Car(4, "", "Green Car", "GM", "Laughter", 1990) to "Unavailable",
        Car(5, "", "Yellow Car", "Honda", "Civic", 2020) to "Out of Stock",
        Car(6, "", "Gold Car", "Chrysler", "Pacifica", 1995) to "In Dealership",
        Car(7, "", "Silver Car", "Jeep", "Cherokee", 1990) to "In Dealership",
        Car(8, "", "Shiny Car", "Hummer", "Viper", 2006) to "Out of Stock",
        Car(9, "", "Black Car", "Dodge", "Caravan", 2005) to "Unavailable",
        Car(10, "", "White Car", "Nissan", "Express", 1990) to "In Dealership"
    )

    fun getCars() {
        FancyCarRepository.getCar()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                cars.value = it
                it?.forEach { car ->
                    getAvailability(car)
                }
            }, {
                Log.d("TAG_X", it.localizedMessage ?: "getCar Error")
            })
    }

    private fun getAvailability(car: Car) {
        FancyCarRepository.getAvailability(car.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                availability.value?.put(car, it.available)
                allCarData = availability.value?.toMap() ?: mapOf()
            }, {
                Log.d("TAG_X", it.localizedMessage ?: "getAvailability Error")
            })
    }

    fun sortByName() {
        allCarData = allCarData.toSortedMap(compareBy { it.name })
    }

    fun sortByAvailability() {
        allCarData = allCarData.toList().sortedBy { (_, v) ->
            v
        }.toMap()
    }
}