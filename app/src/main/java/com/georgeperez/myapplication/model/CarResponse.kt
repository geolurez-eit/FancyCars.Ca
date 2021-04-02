package com.georgeperez.myapplication.model

data class CarResponse(
    var list: List<Car>? = listOf()
) {
    data class Car(
        val id: Int = 0,
        val img: String = "",
        val name: String = "",
        val make: String = "",
        val model: String = "",
        val year: Int = 0
    )
}