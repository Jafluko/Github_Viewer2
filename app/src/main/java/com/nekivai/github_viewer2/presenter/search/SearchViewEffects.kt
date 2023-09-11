package com.nekivai.github_viewer2.presenter.search

sealed class SearchViewEffects {
    data class ShowMessage(val message: String) : SearchViewEffects()
    data class MoveInfoRepo(val ownerName: String, val repoName: String) : SearchViewEffects()
}
