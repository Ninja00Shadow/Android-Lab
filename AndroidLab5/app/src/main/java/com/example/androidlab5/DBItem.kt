package com.example.androidlab5

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class CitySize {
    SMALL, AVERAGE, BIG;

    override fun toString(): String {
        return name
    }
}

@Entity(tableName = "cities")
class DBItem(
    var name: String,
    var description: String,
    var province: String,
    var population: Int,
    var rating: Double,
    var size: CitySize
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    override fun toString(): String {
        return "DataItem(name='$name')"
    }
}