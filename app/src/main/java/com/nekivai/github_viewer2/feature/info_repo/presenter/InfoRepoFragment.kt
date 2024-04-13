package com.nekivai.github_viewer2.feature.info_repo.presenter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nekivai.github_viewer2.R
import com.nekivai.github_viewer2.common.collectUiState
import com.nekivai.github_viewer2.common.getAppComponent
import com.nekivai.github_viewer2.common.loadUriWithCover
import com.nekivai.github_viewer2.databinding.FragmentInfoBinding
import com.nekivai.github_viewer2.feature.info_repo.presenter.adapter.IssueAdapter
import javax.inject.Inject

class InfoRepoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding: FragmentInfoBinding
        get() = checkNotNull(_binding)

    @Inject lateinit var viewModel: InfoViewModel

    private val adapterIssue = IssueAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.onNavigateUp()

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
            globalProgressBar.isVisible = viewState.isLoadInfo()
            mainContainer.isVisible = !viewState.isLoadInfo()
            avatar.loadUriWithCover(
                uri = viewState.avatarUser
            )
            title.text = viewState.fullName
            description.text = viewState.description

            progressBar.isVisible = viewState.isLoadList()
            listIssue.isVisible = !viewState.isLoadList()
            adapterIssue.submitList(viewState.listIssue)

            textIssue.isVisible = viewState.isShowLabelIssue()
            textIssue.text = getString(R.string.issue_empty)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {

        fun newInstance() = InfoRepoFragment()
    }
}