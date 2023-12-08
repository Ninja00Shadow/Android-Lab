package com.example.androidlab4

val list: MutableList<DataItem> = mutableListOf(
    DataItem("Wroclaw", "Capital of Lower Silesia", "Lower Silesia", 631235, 3.0, CitySize.AVERAGE),
    DataItem("Oia", "Small town on Santorini", "South Aegean", 1545, 4.5, CitySize.SMALL),
    DataItem("Hallstatt", "Small town in Austria", "Upper Austria", 859, 3.0, CitySize.SMALL),
    DataItem("Valencia", "City in south-eastern Spain", "Valencian Community", 791413, 4.5, CitySize.AVERAGE),
    DataItem("Portland", "City in Oregon", "Oregon", 654741, 3.5, CitySize.AVERAGE),
    DataItem("Tokio", "Capital of Japan", "Tokyo", 9273000, 4.3, CitySize.BIG),
    DataItem("New York", "City in New York", "New York", 8336817, 4.0, CitySize.BIG),
    DataItem("Warsaw", "Capital of Poland", "Masovian", 1790658, 4.0, CitySize.BIG),
)

class DataRepository {
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