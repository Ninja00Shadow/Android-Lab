package com.example.androidlab4

import kotlin.random.Random

class DataItem {
    var name : String = "Default text"
    var description : String = "Default text"
    var item_value : Int = Random.nextInt(0, 5)
    var item_value2: Int = 0
    var type : Boolean = Random.nextBoolean()
    var item_checked : Boolean = Random.nextBoolean()
    constructor()
    constructor(num: Int) {
        this.name = name + num.toString()
        this.description = description
        this.item_value = item_value
        this.item_value2 = item_value2
        this.type = type
        this.item_checked = item_checked
    }

    override fun toString(): String {
        return "DataItem(name='$name')"
    }

}