package com.example.androidlab4

val list: MutableList<DataItem> = mutableListOf(
    DataItem("Wroclaw", "Capital of Lower Silesia", "Lower Silesia", 631235, 292.82, R.drawable.ic_wroclaw),
    DataItem("Warsaw", "Capital of Poland", "Masovian", 1790658, 517.24, R.drawable.ic_warsaw),
    DataItem("Krakow", "Former capital of Poland", "Lesser Poland", 779115, 326.85, R.drawable.ic_krakow),
    DataItem("Paris", "Capital of France", "Ile-de-France", 2148271, 105.4, R.drawable.ic_paris),
    DataItem("London", "Capital of England", "Greater London", 8908081, 1572.0, R.drawable.ic_london),
    DataItem("Rome", "Capital of Italy", "Lazio", 2872800, 1285.3, R.drawable.ic_rome),
)

class DataRepository {
    val LIST_SIZE = 15
    private var dataList : MutableList<DataItem> = mutableListOf()

    companion object{
        private var INSTANCE: DataRepository? = null
        fun getInstance(): DataRepository {
            if (INSTANCE == null){
                INSTANCE = DataRepository()
            }
            return INSTANCE as DataRepository
        }
    }

    fun getData() : MutableList<DataItem> {
        return dataList
    }

    fun addItem(item: DataItem) {
        dataList.add(item)
    }

    fun deleteItem(pos: Int): Boolean {
        if (pos >= 0 && pos < dataList.size) {
            dataList.removeAt(pos)
            return true
        }
        return false
    }

    init {
        dataList = list
    }

}