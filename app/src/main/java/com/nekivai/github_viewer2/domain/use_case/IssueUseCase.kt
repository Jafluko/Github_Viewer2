package com.nekivai.github_viewer2.domain.use_case

import com.nekivai.github_viewer2.common.Response
import com.nekivai.github_viewer2.domain.models.Issue
import com.nekivai.github_viewer2.domain.repositories.GitRepository
import javax.inject.Inject

class IssueUseCase @Inject constructor(private val repository: GitRepository) {

    suspend fun get(
        owner: String, name: String, limit: Int,
    ): Response<List<Issue>> {
        return repository.getIssuesByRepos(owner, name, limit)
    }
}