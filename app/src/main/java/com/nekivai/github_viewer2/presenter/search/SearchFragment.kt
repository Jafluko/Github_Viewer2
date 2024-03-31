package com.nekivai.github_viewer2.presenter.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.nekivai.github_viewer2.R
import com.nekivai.github_viewer2.common.collectUiEffect
import com.nekivai.github_viewer2.common.collectUiStateByFlow
import com.nekivai.github_viewer2.common.collectUiStateByFlowWithoutSuspend
import com.nekivai.github_viewer2.common.getAppComponent
import com.nekivai.github_viewer2.common.safeNavigate
import com.nekivai.github_viewer2.common.showToast
import com.nekivai.github_viewer2.databinding.FragmentSearchBinding
import com.nekivai.github_viewer2.domain.models.SearchItem
import com.nekivai.github_viewer2.presenter.search.adapter.FooterAdapter
import com.nekivai.github_viewer2.presenter.search.adapter.SearchAdapter
import javax.inject.Inject

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = checkNotNull(_binding)

    @Inject lateinit var viewModel: SearchViewModel

    private val listAdapter: SearchAdapter by lazy {
        SearchAdapter(
            onClick = viewModel::moveInfoRepo
        )
    }

    private var searchView: SearchView? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        getAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? MenuHost)?.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.toolbar_menu, menu)
                    val searchViewItem = menu.findItem(R.id.search_bar)
                    searchView = (searchViewItem.actionView as? SearchView)
                    setSearchViewListener()
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return false
                }

            }, viewLifecycleOwner
        )

        binding.setAdapter()
        collectUiStateByFlow(viewModel.viewState, ::updateState)
        collectUiEffect(viewModel.viewEffects, ::reactTo)
        collectUiStateByFlowWithoutSuspend(listAdapter.loadStateFlow, ::loadState)

        binding.btnRetry.setOnClickListener {
            listAdapter.refresh()
        }
    }

    private fun FragmentSearchBinding.setAdapter() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter.withLoadStateFooter(
                FooterAdapter(listAdapter::retry)
            )
        }
    }

    private suspend fun updateState(state: PagingData<SearchItem>) {
        listAdapter.submitData(state)
    }

    private fun reactTo(viewEffects: SearchViewEffects) {
        when (viewEffects) {
            is SearchViewEffects.ShowMessage -> {
                showToast(viewEffects.message)
            }

            is SearchViewEffects.MoveInfoRepo -> {
                safeNavigate(
                    SearchFragmentDirections.actionSearchToInfo(
                        ownerName = viewEffects.ownerName,
                        repoName = viewEffects.repoName,
                    )
                )
            }
        }
    }

    private fun setSearchViewListener() {
        searchView?.setOnQueryTextListener(
            object : OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newValue: String?): Boolean {
                    viewModel.changeContext(newValue)
                    return false
                }

            }
        )
    }

    private fun loadState(loadStates: CombinedLoadStates) {
        binding.apply {
            progressBar.isVisible = loadStates.refresh is LoadState.Loading
            btnRetry.isVisible =
                loadStates.refresh !is LoadState.Loading && loadStates.refresh is LoadState.Error
            tvError.isVisible = loadStates.refresh is LoadState.Error
            recyclerView.isVisible = loadStates.refresh !is LoadState.Error
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}