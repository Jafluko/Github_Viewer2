package com.nekivai.github_viewer2.presenter.info.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nekivai.github_viewer2.common.loadUriWithCover
import com.nekivai.github_viewer2.databinding.ItemIssueBinding
import com.nekivai.github_viewer2.domain.models.Issue

class IssueAdapter : ListAdapter<Issue, IssueAdapter.IssueViewHolder>(IssueDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return IssueViewHolder(
            ItemIssueBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class IssueViewHolder(
        private val binding: ItemIssueBinding
    ) : ViewHolder(binding.root) {

        fun onBind(item: Issue) {
            binding.apply {
                avatar.loadUriWithCover(
                    uri = item.owner.avatarUrl,
                )
                title.text = item.title
                status.text = item.state
                description.text = item.body
            }
        }
    }
}