package com.nekivai.github_viewer2.feature.info_repo.data.dto

import com.google.gson.annotations.SerializedName
import com.nekivai.github_viewer2.common.orZero
import com.nekivai.github_viewer2.feature.info_repo.domain.models.Owner

data class OwnerDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("login") val login: String?,
    @SerializedName("avatar_url") val avatarUrl: String?,
) {
    fun toDomain(): Owner =
        Owner(
            id = this.id.orZero(),
            login = this.login.orEmpty(),
            avatarUrl = this.avatarUrl.orEmpty(),
        )
}
