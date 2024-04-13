package com.nekivai.github_viewer2.feature.search.presenter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nekivai.github_viewer2.feature.search.domain.models.SearchItem
import com.nekivai.github_viewer2.feature.search.domain.use_case.SearchUserCase
import javax.inject.Inject
import kotlin.Exception

class SearchPagingSource @Inject constructor(
    private val searchUserCase: SearchUserCase,
    private val context: String,
) : PagingSource<Int, SearchItem>() {
    override fun getRefreshKey(state: PagingState<Int, SearchItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchItem> {
        return try {
            val nextPageNumber = params.key ?: START_PAGE
            val response = searchUserCase.search(context, nextPageNumber, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (nextPageNumber == START_PAGE) null else nextPageNumber.minus(1),
                nextKey = if (response.isEmpty()) null else nextPageNumber.plus(1),
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val START_PAGE = 1
    }
}