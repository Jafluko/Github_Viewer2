package com.nekivai.github_viewer2.common

sealed class GitException : Exception() {

    object ResponseDtoEqualNullException : GitException()
    data class MapperException(override val cause: Throwable) : GitException()
}
