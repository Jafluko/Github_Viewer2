package com.nekivai.github_viewer2.feature.search.domain.repositories

import com.nekivai.github_viewer2.feature.search.domain.models.SearchItem

interface SearchRepository {

    suspend fun search(context: String, page: Int, limit: Int): List<SearchItem>
}