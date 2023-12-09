package com.example.androidlab5

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MyDao {
    @Query("SELECT * FROM cities")
    fun getAll(): MutableList<DBItem>?

    @Query("SELECT * FROM cities WHERE id = :id")
    fun getById(id: Int): DBItem

    @Query("DELETE FROM cities")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: DBItem) : Long

    @Update
    fun update(item: DBItem) : Int

    @Delete
    fun delete(item: DBItem) : Int
}