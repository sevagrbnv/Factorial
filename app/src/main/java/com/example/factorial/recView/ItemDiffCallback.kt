package com.example.factorial.recView

import androidx.recyclerview.widget.DiffUtil
import com.example.factorial.HistoryItem

class ItemDiffCallback : DiffUtil.ItemCallback<HistoryItem>() {

    override fun areItemsTheSame(oldItem: HistoryItem, newItem: HistoryItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: HistoryItem, newItem: HistoryItem): Boolean =
        oldItem == newItem
}