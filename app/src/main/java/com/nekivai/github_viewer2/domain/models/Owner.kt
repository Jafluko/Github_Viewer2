package com.nekivai.github_viewer2.domain.models

import com.nekivai.github_viewer2.common.EMPTY_STRING
import com.nekivai.github_viewer2.common.ZERO_INT

data class Owner(
    val id: Int = ZERO_INT,
    val login: String = EMPTY_STRING,
    val avatarUrl: String = EMPTY_STRING,
)
