package com.nekivai.github_viewer2.feature.info_repo.domain.models

import com.nekivai.github_viewer2.common.EMPTY_STRING
import com.nekivai.github_viewer2.common.ZERO_INT

data class Issue(
    val id: Int = ZERO_INT,
    val title: String = EMPTY_STRING,
    val owner: Owner = Owner(),
    val state: String = EMPTY_STRING,
    val body: String = EMPTY_STRING,
)
