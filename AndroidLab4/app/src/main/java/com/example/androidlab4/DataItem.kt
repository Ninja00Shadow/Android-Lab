package com.example.androidlab4

import kotlin.random.Random

class DataItem(
    var name: String,
    var description: String,
    var province: String,
    var population: Int,
    var area: Double,
    var icon: Int
) {

    override fun toString(): String {
        return "DataItem(name='$name')"
    }

}