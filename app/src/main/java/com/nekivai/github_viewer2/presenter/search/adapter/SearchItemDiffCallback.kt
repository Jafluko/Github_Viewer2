package com.nekivai.github_viewer2.presenter.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nekivai.github_viewer2.domain.models.SearchItem

class SearchItemDiffCallback : DiffUtil.ItemCallback<SearchItem>() {
    override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
        return oldItem == newItem
    }
}