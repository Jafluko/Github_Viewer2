package com.nekivai.github_viewer2.feature.info_repo.domain.models

import com.nekivai.github_viewer2.common.EMPTY_STRING
import com.nekivai.github_viewer2.common.ZERO_INT

data class Repo(
    val id: Int = ZERO_INT,
    val name: String = EMPTY_STRING,
    val owner: Owner = Owner(),
    val description: String = EMPTY_STRING,
)
