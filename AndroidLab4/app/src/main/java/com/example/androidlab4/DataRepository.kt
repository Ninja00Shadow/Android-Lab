package com.example.androidlab4

class DataRepository {
    val LIST_SIZE = 15
    private lateinit var dataList : MutableList<DataItem>

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
        dataList = MutableList(LIST_SIZE) { i -> DataItem(i) }
    }
}