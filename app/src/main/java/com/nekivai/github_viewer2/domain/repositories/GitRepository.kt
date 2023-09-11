package com.nekivai.github_viewer2.domain.repositories

import com.nekivai.github_viewer2.common.Response
import com.nekivai.github_viewer2.domain.models.Issue
import com.nekivai.github_viewer2.domain.models.Repo
import com.nekivai.github_viewer2.domain.models.SearchItem

interface GitRepository {

    suspend fun search(context: String, page: Int, limit: Int): List<SearchItem>

    suspend fun getInfoByRepo(owner: String, name: String): Response<Repo>

    suspend fun getIssuesByRepos(
        owner: String, name: String, limit: Int,
    ): Response<List<Issue>>
}