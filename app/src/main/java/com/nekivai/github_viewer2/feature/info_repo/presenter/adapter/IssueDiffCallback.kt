package com.nekivai.github_viewer2.feature.info_repo.presenter.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nekivai.github_viewer2.feature.info_repo.domain.models.Issue

class IssueDiffCallback : DiffUtil.ItemCallback<Issue>() {
    override fun areItemsTheSame(oldItem: Issue, newItem: Issue): Boolean {
        return  oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Issue, newItem: Issue): Boolean {
        return oldItem == newItem
    }
}