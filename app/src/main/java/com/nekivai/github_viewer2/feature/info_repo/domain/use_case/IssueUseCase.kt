package com.nekivai.github_viewer2.feature.info_repo.domain.use_case

import com.nekivai.github_viewer2.common.Response
import com.nekivai.github_viewer2.feature.info_repo.domain.models.Issue
import com.nekivai.github_viewer2.feature.info_repo.domain.repositories.InfoRepoRepository
import javax.inject.Inject

class IssueUseCase @Inject constructor(private val repository: InfoRepoRepository) {

    suspend fun get(
        owner: String, name: String, limit: Int,
    ): Response<List<Issue>> {
        return repository.getIssuesByRepos(owner, name, limit)
    }
}