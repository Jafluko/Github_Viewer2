package com.nekivai.github_viewer2.data.source.network

import com.nekivai.github_viewer2.data.source.dto.IssueDto
import com.nekivai.github_viewer2.data.source.dto.RepoDto
import com.nekivai.github_viewer2.data.source.dto.ResponseDto
import com.nekivai.github_viewer2.data.source.dto.SearchItemDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitApi {

    @GET("search/repositories")
    suspend fun search(
        @Query("q") context: String,
        @Query("page") page: Int,
        @Query("per_page") limit: Int,
    ): ResponseDto<List<SearchItemDto>>

    @GET("repos/{owner}/{repo}")
    suspend fun getInfo(
        @Path("owner") owner: String,
        @Path("repo") repoName: String,
    ): RepoDto

    @GET("repos/{owner}/{repo}/issues")
    suspend fun getIssuesByRepos(
        @Path("owner") owner: String,
        @Path("repo") repoName: String,
        @Query("per_page") limit: Int,
    ): List<IssueDto>
}