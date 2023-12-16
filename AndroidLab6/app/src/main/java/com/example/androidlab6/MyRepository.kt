package com.example.androidlab6

import android.content.Context

val list: MutableList<DBItem> = mutableListOf(
    DBItem("Wroclaw", "Capital of Lower Silesia", "Lower Silesia", 631235, 3.0, CitySize.AVERAGE),
    DBItem("Oia", "Small town on Santorini", "South Aegean", 1545, 4.5, CitySize.SMALL),
    DBItem("Hallstatt", "Small town in Austria", "Upper Austria", 859, 3.0, CitySize.SMALL),
    DBItem("Valencia", "City in south-eastern Spain", "Valencian Community", 791413, 4.5, CitySize.AVERAGE),
    DBItem("Portland", "City in Oregon", "Oregon", 654741, 3.5, CitySize.AVERAGE),
    DBItem("Tokio", "Capital of Japan", "Tokyo", 9273000, 4.3, CitySize.BIG),
    DBItem("New York", "City in New York", "New York", 8336817, 4.0, CitySize.BIG),
    DBItem("Warsaw", "Capital of Poland", "Masovian", 1790658, 4.0, CitySize.BIG),
)

class MyRepository(context: Context) {
    private var dataList : MutableList<DBItem>? = null
    private var myDao: MyDao
    private var db: MyDB? = null

    companion object{
        private var R_INSTANCE: MyRepository? = null
        fun getInstance(context: Context): MyRepository {
            if (R_INSTANCE == null){
                R_INSTANCE = MyRepository(context)
            }
            return R_INSTANCE as MyRepository
        }
    }

    fun getData() : MutableList<DBItem>? {
        dataList = myDao.getAll()
        return dataList
    }

    fun addItem(item: DBItem) : Boolean {
        return myDao.insert(item) != -1L
    }

    fun modifyItem(item: DBItem) : Boolean {
        return myDao.update(item) >= 0
    }

    fun deleteItem(item : DBItem): Boolean {
        return myDao.delete(item) >= 0
    }

    init {
        db = MyDB.getDatabase(context)
        myDao = db?.myDao()!!

//        for (item in list) {
//            if (myDao.insert(item) == -1L) {
//                throw Exception("Error while inserting data")
//            }
//        }
    }

}