package com.example.kolomachinetest.api.repo.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Search(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "key") val key: String?,
)