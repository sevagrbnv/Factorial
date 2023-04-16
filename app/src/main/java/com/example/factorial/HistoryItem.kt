package com.example.factorial

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class HistoryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val expression: String
)