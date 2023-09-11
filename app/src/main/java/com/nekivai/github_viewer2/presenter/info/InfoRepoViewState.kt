package com.nekivai.github_viewer2.presenter.info

import com.nekivai.github_viewer2.common.EMPTY_STRING
import com.nekivai.github_viewer2.domain.models.Issue

data class InfoRepoViewState(
    val avatarUser: String = EMPTY_STRING,
    val fullName: String = EMPTY_STRING,
    val description: String = EMPTY_STRING,
    val listIssue: List<Issue> = emptyList(),
)
