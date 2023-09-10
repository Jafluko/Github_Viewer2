package com.nekivai.github_viewer2.common

sealed class Response<T> {

    data class Success<T>(
        val data: T,
    ) : Response<T>()

    data class Error<T>(val uiText: UiText = UiText.unknownError()) : Response<T>()
}
