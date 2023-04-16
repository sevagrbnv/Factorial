package com.example.factorial

import android.app.Application
import androidx.lifecycle.LiveData

class DatabaseRepository(
    private val itemDao: HistoryItemDao
) {

    suspend fun addItem(item: HistoryItem) = itemDao.addItem(item)

    suspend fun deleteItem(item: HistoryItem) = item.id?.let { itemDao.deleteItem(it) }

    suspend fun getItem(itemId: Int): HistoryItem = itemDao.getItem(itemId)

    fun getItemList(): LiveData<List<HistoryItem>> = itemDao.getItemList()


    companion object {
        @Volatile
        private var instance: DatabaseRepository? = null

        fun getInstance(itemDao: HistoryItemDao) =
            instance ?: synchronized(this) {
                instance ?: DatabaseRepository(itemDao).also { instance = it }
            }
    }
}