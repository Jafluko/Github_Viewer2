package com.nekivai.github_viewer2.data.repositories

import com.nekivai.github_viewer2.common.Response
import com.nekivai.github_viewer2.data.source.network.GitApi
import com.nekivai.github_viewer2.domain.models.Issue
import com.nekivai.github_viewer2.domain.models.Repo
import com.nekivai.github_viewer2.domain.models.SearchItem
import com.nekivai.github_viewer2.domain.repositories.GitRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GitRepositoryImpl @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val api: GitApi,
) : Repository(dispatcher), GitRepository {
    override suspend fun search(
        context: String,
        page: Int,
        limit: Int
    ): Response<List<SearchItem>> {
        return wrapRequest(
            request = { api.search(context, page, limit) },
            mapper = { data -> data.map { it.toDomain() } }
        )
    }

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