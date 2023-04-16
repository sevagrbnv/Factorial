package com.example.factorial

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryItemDao {

    @Insert(entity = HistoryItem::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: HistoryItem)

    @Query("DELETE FROM item WHERE id = (:itemId)")
    suspend fun deleteItem(itemId: Int)

    @Query("SELECT * FROM item WHERE id = (:itemId) LIMIT 1")
    suspend fun getItem(itemId: Int): HistoryItem

    @Query("SELECT * FROM item ORDER BY id DESC")
    fun getItemList(): LiveData<List<HistoryItem>>
}