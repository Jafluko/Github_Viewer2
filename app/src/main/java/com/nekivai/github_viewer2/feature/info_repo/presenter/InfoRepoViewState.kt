package com.nekivai.github_viewer2.feature.info_repo.presenter

import com.nekivai.github_viewer2.common.EMPTY_STRING
import com.nekivai.github_viewer2.feature.info_repo.domain.models.Issue

data class InfoRepoViewState(
    val avatarUser: String = EMPTY_STRING,
    val fullName: String = EMPTY_STRING,
    val description: String = EMPTY_STRING,
    val listIssue: List<Issue> = emptyList(),
    val isLoadEnd: Boolean = false,
) {
    fun isLoadInfo(): Boolean = avatarUser.isBlank() || fullName.isBlank() || description.isBlank()
    fun isLoadList(): Boolean = listIssue.isEmpty() && !isLoadEnd
    fun isShowLabelIssue(): Boolean = listIssue.isEmpty() && isLoadEnd
}
