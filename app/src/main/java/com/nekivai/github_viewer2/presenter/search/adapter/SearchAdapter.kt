package com.nekivai.github_viewer2.presenter.search.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nekivai.github_viewer2.R
import com.nekivai.github_viewer2.common.loadUriWithCover
import com.nekivai.github_viewer2.databinding.ItemSearchBinding
import com.nekivai.github_viewer2.domain.models.SearchItem

class SearchAdapter(
    private val onClick: (owner: String, repo: String) -> Unit,
) : PagingDataAdapter<SearchItem, SearchAdapter.SearchItemViewHolder>(SearchItemDiffCallback()) {
    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        Log.d("Adapter", "createHolder")
        val layoutInflater = LayoutInflater.from(parent.context)
        return SearchItemViewHolder(
            binding = ItemSearchBinding.inflate(layoutInflater, parent, false)
        )
    }

    inner class SearchItemViewHolder(
        private val binding: ItemSearchBinding
    ) : ViewHolder(binding.root) {

        fun bind(item: SearchItem?) {
            item ?: return
            binding.apply {
                avatar.loadUriWithCover(
                    uri = item.owner.avatarUrl,
                    placeHolderId = R.drawable.pic_avatar_placeholder,
                )
                title.text = "${item.owner.login}/${item.name}"
                description.text = item.description
                root.setOnClickListener {
                    onClick(item.owner.login, item.name)
                }
            }
        }
    }
}