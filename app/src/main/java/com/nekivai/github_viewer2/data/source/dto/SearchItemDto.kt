package com.nekivai.github_viewer2.data.source.dto

import com.google.gson.annotations.SerializedName
import com.nekivai.github_viewer2.common.orZero
import com.nekivai.github_viewer2.domain.models.Owner
import com.nekivai.github_viewer2.domain.models.SearchItem

data class SearchItemDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("owner") val owner: OwnerDto?,
    @SerializedName("description") val description: String?,
) {

    fun toDomain(): SearchItem =
        SearchItem(
            id = this.id.orZero(),
            name = this.name.orEmpty(),
            owner = this.owner?.toDomain() ?: Owner(),
            description = this.description.orEmpty(),
        )
}
