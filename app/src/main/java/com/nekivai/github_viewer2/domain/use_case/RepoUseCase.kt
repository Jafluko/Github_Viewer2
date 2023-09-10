package com.nekivai.github_viewer2.domain.use_case

import com.nekivai.github_viewer2.common.Response
import com.nekivai.github_viewer2.domain.models.Repo
import com.nekivai.github_viewer2.domain.repositories.GitRepository
import javax.inject.Inject

class RepoUseCase @Inject constructor(private val repository: GitRepository) {

    suspend fun get(owner: String, name: String): Response<Repo> {
        return repository.getInfoByRepo(owner, name)
    }
}