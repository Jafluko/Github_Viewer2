package com.nekivai.github_viewer2.feature.info_repo.data

import com.nekivai.github_viewer2.common.Response
import com.nekivai.github_viewer2.data.repositories.Repository
import com.nekivai.github_viewer2.data.source.network.GitApi
import com.nekivai.github_viewer2.feature.info_repo.domain.models.Issue
import com.nekivai.github_viewer2.feature.info_repo.domain.models.Repo
import com.nekivai.github_viewer2.feature.info_repo.domain.repositories.InfoRepoRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class InfoRepoRepositoryImpl @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val api: GitApi,
) : Repository(dispatcher), InfoRepoRepository {

    override suspend fun getInfoByRepo(owner: String, name: String): Response<Repo> {
        return wrapRequest(
            request = { api.getInfo(owner, name) },
            mapper = { it.toDomain() }
        )
    }

    override suspend fun getIssuesByRepos(
        owner: String,
        name: String,
        limit: Int
    ): Response<List<Issue>> {
        return wrapRequest(
            request = { api.getIssuesByRepos(owner, name, limit) },
            mapper = { data -> data.map { it.toDomain() } }
        )
    }
}