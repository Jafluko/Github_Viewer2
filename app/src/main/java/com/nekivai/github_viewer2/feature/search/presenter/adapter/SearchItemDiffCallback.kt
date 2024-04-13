package com.nekivai.github_viewer2.feature.search.presenter.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nekivai.github_viewer2.feature.search.domain.models.SearchItem

class SearchItemDiffCallback : DiffUtil.ItemCallback<SearchItem>() {
    override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
        return oldItem == newItem
    }
}