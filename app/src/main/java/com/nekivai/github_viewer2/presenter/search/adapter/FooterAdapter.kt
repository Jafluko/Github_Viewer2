package com.nekivai.github_viewer2.presenter.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nekivai.github_viewer2.databinding.FooterBinding

class FooterAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<FooterViewHolder>() {
    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FooterViewHolder(
            FooterBinding.inflate(layoutInflater, parent, false),
            retry
        )
    }

}

class FooterViewHolder(
    private val binding: FooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        binding.apply {
            progressBar.isVisible = loadState is LoadState.Loading
            tvError.isVisible = loadState is LoadState.Error
            btnRetry.isVisible = loadState is LoadState.Error
        }
    }
}