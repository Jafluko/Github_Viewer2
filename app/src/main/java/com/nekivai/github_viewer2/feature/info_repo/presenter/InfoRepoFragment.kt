package com.nekivai.github_viewer2.feature.info_repo.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nekivai.android.ComponentRegistry
import com.nekivai.android.ComponentRegistry.registerComponent
import com.nekivai.github_viewer2.AppComponent
import com.nekivai.github_viewer2.R
import com.nekivai.github_viewer2.common.collectUiState
import com.nekivai.github_viewer2.common.loadUriWithCover
import com.nekivai.github_viewer2.core.assistedViewModel
import com.nekivai.github_viewer2.databinding.FragmentInfoBinding
import com.nekivai.github_viewer2.feature.info_repo.di.DaggerInfoRepositoryComponent
import com.nekivai.github_viewer2.feature.info_repo.di.InfoRepositoryComponent
import com.nekivai.github_viewer2.feature.info_repo.presenter.adapter.IssueAdapter

class InfoRepoFragment : Fragment() {

    private val component: InfoRepositoryComponent by registerComponent {
        DaggerInfoRepositoryComponent
            .factory()
            .create(ComponentRegistry.findComponent(AppComponent::class))
    }

    private var _binding: FragmentInfoBinding? = null
    private val binding: FragmentInfoBinding
        get() = checkNotNull(_binding)

    private val viewModel: InfoViewModel by assistedViewModel {
        component.provideInfoViewModelFactory().create(
            ownerName = arguments?.getString(KEY_OWNER_NAME),
            repoName = arguments?.getString(KEY_REPO_NAME),
        )
    }

    private val adapterIssue = IssueAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
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

        private const val KEY_OWNER_NAME = "KEY_OWNER_NAME"
        private const val KEY_REPO_NAME = "KEY_REPO_NAME"

        fun newInstance(
            ownerName: String?,
            repoName: String?
        ) = InfoRepoFragment().apply {
            arguments = bundleOf(
                KEY_OWNER_NAME to ownerName,
                KEY_REPO_NAME to repoName,
            )
        }
    }
}