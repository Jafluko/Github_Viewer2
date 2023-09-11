package com.nekivai.github_viewer2.presenter.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nekivai.github_viewer2.R
import com.nekivai.github_viewer2.common.collectUiState
import com.nekivai.github_viewer2.common.loadUriWithCover
import com.nekivai.github_viewer2.databinding.FragmentInfoBinding
import com.nekivai.github_viewer2.presenter.info.adapter.IssueAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoRepoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding: FragmentInfoBinding
        get() = checkNotNull(_binding)

    private val viewModel: InfoViewModel by viewModels()

    private val adapterIssue = IssueAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.setAdapter()
        collectUiState(viewModel.viewState, ::updateStateView)
    }

    private fun FragmentInfoBinding.setAdapter() {
        listIssue.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterIssue
        }
    }

    private fun updateStateView(viewState: InfoRepoViewState) {
        binding.apply {
            avatar.loadUriWithCover(
                uri = viewState.avatarUser
            )
            title.text = viewState.fullName
            description.text = viewState.description
            adapterIssue.submitList(viewState.listIssue)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}