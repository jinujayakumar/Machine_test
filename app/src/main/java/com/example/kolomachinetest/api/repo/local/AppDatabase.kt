package com.example.kolomachinetest.api.repo.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Search::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): SearchDao
}