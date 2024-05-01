package com.nekivai.github_viewer2.feature.info_repo.data.dto

import com.google.gson.annotations.SerializedName
import com.nekivai.github_viewer2.common.orZero
import com.nekivai.github_viewer2.feature.info_repo.domain.models.Owner
import com.nekivai.github_viewer2.feature.info_repo.domain.models.Repo

data class RepoDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("owner") val owner: OwnerDto?,
    @SerializedName("description") val description: String?,
) {
    fun toDomain(): Repo =
        Repo(
            id = this.id.orZero(),
            name = this.name.orEmpty(),
            owner = this.owner?.toDomain() ?: Owner(),
            description = this.description.orEmpty(),
        )
}
