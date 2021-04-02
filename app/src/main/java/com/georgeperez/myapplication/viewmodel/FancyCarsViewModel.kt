package com.georgeperez.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.georgeperez.myapplication.model.CarResponse
import com.georgeperez.myapplication.model.FancyCarRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object FancyCarsViewModel {
    val cars = MutableLiveData<CarResponse>()
    val availability = MutableLiveData<HashMap<Int, String>>()

    fun getCar() {
        FancyCarRepository.getCar()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                cars.value = it
                it.list?.forEach { car ->
                    getAvailability(car.id)
                }
            }, {
                Log.d("TAG_X", it.localizedMessage ?: "getCar Error")
            })
    }

    private fun getAvailability(id: Int) {
        FancyCarRepository.getAvailability(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                availability.value?.put(id, it.available)
            }, {
                Log.d("TAG_X", it.localizedMessage ?: "getAvailability Error")
            })
    }
}