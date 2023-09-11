package com.nekivai.github_viewer2.presenter.info

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nekivai.github_viewer2.common.Response
import com.nekivai.github_viewer2.domain.use_case.IssueUseCase
import com.nekivai.github_viewer2.domain.use_case.RepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repoUseCase: RepoUseCase,
    private val issueUseCase: IssueUseCase,
) : ViewModel() {

    private val ownerName: String? = savedStateHandle["ownerName"]
    private val repoName: String? = savedStateHandle["repoName"]

    private val _viewState = MutableStateFlow(InfoRepoViewState())
    val viewState = _viewState.asStateFlow()

    private val _viewEffects = MutableSharedFlow<InfoRepoViewEffects>()
    val viewEffects = _viewEffects.asSharedFlow()

    init {
        viewModelScope.launch {
            fetchInfo()
            fetchListIssue()
        }
    }

    private suspend fun fetchInfo() {
        if (ownerName == null || repoName == null) {
            _viewEffects.emit(InfoRepoViewEffects.MoveBack)
            return
        }
        when(val response = repoUseCase.get(ownerName, repoName)) {
            is Response.Success -> {
                _viewState.value = _viewState.value.copy(
                    avatarUser = response.data.owner.avatarUrl,
                    fullName = "${response.data.owner.login}/${response.data.name}",
                    description = response.data.description
                )
            }
            is Response.Error -> {
                _viewEffects.emit(InfoRepoViewEffects.ShowMessage(response.uiText))
            }
        }
    }

    private suspend fun fetchListIssue() {
        if (ownerName == null || repoName == null) {
            _viewEffects.emit(InfoRepoViewEffects.MoveBack)
            return
        }
        when(val response = issueUseCase.get(ownerName, repoName, DEFAULT_LIMIT)) {
            is Response.Success -> {
                _viewState.value = _viewState.value.copy(
                    listIssue = response.data,
                    isLoadEnd = true,
                )
            }
            is Response.Error -> {
                _viewEffects.emit(InfoRepoViewEffects.ShowMessage(response.uiText))
            }
        }
    }

    companion object {
        private const val DEFAULT_LIMIT = 30
    }
}