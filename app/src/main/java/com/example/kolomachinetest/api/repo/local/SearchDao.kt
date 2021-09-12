package com.example.kolomachinetest.api.repo.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchDao {

    @Query("SELECT * FROM search")
    fun getSearchList(): List<Search>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchQuery(vararg: Search)

    @Query("SELECT * FROM search WHERE `key` IN (:userIds)")
    fun searchListById(userIds: String): List<Search>
}