package com.nekivai.github_viewer2.presenter.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nekivai.github_viewer2.domain.models.SearchItem
import com.nekivai.github_viewer2.domain.use_case.SearchUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.switchMap
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUserCase: SearchUserCase,
) : ViewModel() {

    private val _contextQuery = MutableStateFlow(EMPTY_CONTEXT)
    private var searchJob: Job? = null

    private val _viewEffects = MutableSharedFlow<SearchViewEffects>()
    val viewEffects = _viewEffects.asSharedFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val viewState = _contextQuery.flatMapLatest { context ->
        Pager(
            PagingConfig(
                pageSize = DEFAULT_LIMIT
            )
        ) {
            SearchPagingSource(
                searchUserCase,
                context
            )
        }.flow.cachedIn(viewModelScope)
    }

    fun changeContext(context: String?) {
        searchJob?.cancel()
        searchJob = null
        searchJob = viewModelScope.launch {
            delay(DEFAULT_DELAY_PRINTING)
            if (isActive) {
                _contextQuery.value = if (context.isNullOrBlank()) EMPTY_CONTEXT else context
            }
        }
    }

    fun moveInfoRepo(ownerName: String, repoName: String) {
        viewModelScope.launch {
            _viewEffects.emit(SearchViewEffects.MoveInfoRepo(ownerName, repoName))
        }
    }

    companion object {
        private const val EMPTY_CONTEXT = "null"
        private const val DEFAULT_LIMIT = 16
        private const val DEFAULT_DELAY_PRINTING = 300L
    }
}