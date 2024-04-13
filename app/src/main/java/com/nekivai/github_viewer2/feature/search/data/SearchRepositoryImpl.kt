package com.nekivai.github_viewer2.feature.search.data

import com.nekivai.github_viewer2.data.repositories.Repository
import com.nekivai.github_viewer2.data.source.network.GitApi
import com.nekivai.github_viewer2.feature.search.domain.repositories.SearchRepository
import com.nekivai.github_viewer2.feature.search.domain.models.SearchItem
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val api: GitApi,
) : Repository(dispatcher), SearchRepository {

    override suspend fun search(
        context: String,
        page: Int,
        limit: Int
    ): List<SearchItem> {
        return wrapRequestWithotResponse(
            request = { api.search(context, page, limit) },
            mapper = { data -> data.items?.map { it.toDomain() } ?: emptyList() }
        )
    }
}