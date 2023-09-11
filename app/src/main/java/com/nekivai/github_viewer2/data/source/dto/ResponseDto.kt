package com.nekivai.github_viewer2.data.source.dto

import com.google.gson.annotations.SerializedName

data class ResponseDto<T>(
    @SerializedName("items") val items: T?,
)
