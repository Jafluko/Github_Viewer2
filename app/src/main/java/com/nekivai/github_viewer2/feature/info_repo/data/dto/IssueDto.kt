package com.nekivai.github_viewer2.feature.info_repo.data.dto

import com.google.gson.annotations.SerializedName
import com.nekivai.github_viewer2.common.orZero
import com.nekivai.github_viewer2.feature.info_repo.domain.models.Issue
import com.nekivai.github_viewer2.feature.info_repo.domain.models.Owner

data class IssueDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("owner") val owner: OwnerDto?,
    @SerializedName("state") val state: String?,
    @SerializedName("body") val body: String?,
) {
    fun toDomain(): Issue =
        Issue(
            id = this.id.orZero(),
            title = this.title.orEmpty(),
            owner = this.owner?.toDomain() ?: Owner(),
            state = this.state.orEmpty(),
            body = this.body.orEmpty(),
        )
}
