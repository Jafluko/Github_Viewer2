package com.nekivai.github_viewer2.domain.use_case

import com.nekivai.github_viewer2.common.Response
import com.nekivai.github_viewer2.domain.models.SearchItem
import com.nekivai.github_viewer2.domain.repositories.GitRepository
import javax.inject.Inject

class SearchUserCase @Inject constructor(private val repository: GitRepository) {

    suspend fun search(
        context: String,
        page: Int,
        limit: Int,
    ): Response<List<SearchItem>> {
        return repository.search(context, page, limit)
    }
}