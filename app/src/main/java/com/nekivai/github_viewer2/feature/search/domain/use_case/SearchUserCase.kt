package com.nekivai.github_viewer2.feature.search.domain.use_case

import com.nekivai.github_viewer2.feature.search.domain.models.SearchItem
import com.nekivai.github_viewer2.feature.search.domain.repositories.SearchRepository
import javax.inject.Inject

class SearchUserCase @Inject constructor(private val repository: SearchRepository) {

    suspend fun search(
        context: String,
        page: Int,
        limit: Int,
    ): List<SearchItem> {
        return repository.search(context, page, limit)
    }
}