package com.nekivai.github_viewer2.presenter.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.nekivai.github_viewer2.common.collectUiState
import com.nekivai.github_viewer2.common.collectUiStateFlow
import com.nekivai.github_viewer2.databinding.FragmentSearchBinding
import com.nekivai.github_viewer2.domain.models.SearchItem
import com.nekivai.github_viewer2.presenter.search.adapter.FooterAdapter
import com.nekivai.github_viewer2.presenter.search.adapter.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = checkNotNull(_binding)

    private val viewModel: SearchViewModel by viewModels()

    private val listAdapter = SearchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setAdapter()
        collectUiStateFlow(viewModel.viewState, ::updateState)
    }

    private fun FragmentSearchBinding.setAdapter() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter.withLoadStateFooter(
                FooterAdapter {
                    listAdapter::retry
                }
            )
        }
    }

    private suspend fun updateState(state: PagingData<SearchItem>) {
        listAdapter.submitData(state)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}