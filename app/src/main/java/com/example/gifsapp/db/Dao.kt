package com.example.gifsapp.db

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    @Query("SELECT * FROM id")
    suspend fun getAll(): List<Id>

//    @Query("SELECT * FROM newentity WHERE title LIKE :first AND " +
//            "subtitle LIKE :last LIMIT 1")
//    suspend fun findByName(first: String, last: String): NewEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg id: Id)

//    @Delete
//    suspend fun delete(user: NewEntity)
}