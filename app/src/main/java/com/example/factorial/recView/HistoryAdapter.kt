package com.example.factorial.recView

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.factorial.HistoryItem
import com.example.factorial.R
import com.example.factorial.databinding.HistoryItemBinding

class HistoryAdapter : ListAdapter<HistoryItem, ItemViewHolder>(
    ItemDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.history_item,
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding

        when (binding) {
            is HistoryItemBinding -> {
                binding.historyItem.text = item.expression
                if (getItemViewType(position) == 0)
                    binding.historyItem.setTypeface(null, Typeface.BOLD)
                else binding.historyItem.setTypeface(null, Typeface.NORMAL)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return 0
        } else {
            return 1
        }
    }

}