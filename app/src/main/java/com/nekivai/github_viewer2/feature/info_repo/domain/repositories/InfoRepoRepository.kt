package com.nekivai.github_viewer2.feature.info_repo.domain.repositories

import com.nekivai.github_viewer2.common.Response
import com.nekivai.github_viewer2.feature.info_repo.domain.models.Issue
import com.nekivai.github_viewer2.feature.info_repo.domain.models.Repo

interface InfoRepoRepository {

    suspend fun getInfoByRepo(owner: String, name: String): Response<Repo>

    suspend fun getIssuesByRepos(
        owner: String, name: String, limit: Int,
    ): Response<List<Issue>>
}