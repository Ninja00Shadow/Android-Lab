package com.example.androidlab4

import kotlin.random.Random

enum class CitySize {
    SMALL, AVERAGE, BIG
}

class DataItem(
    var name: String,
    var description: String,
    var province: String,
    var population: Int,
    var rating: Double,
    var size: CitySize,
) {

    override fun toString(): String {
        return "DataItem(name='$name')"
    }

}