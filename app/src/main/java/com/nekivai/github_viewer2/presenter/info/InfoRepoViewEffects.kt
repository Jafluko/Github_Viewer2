package com.nekivai.github_viewer2.presenter.info

import com.nekivai.github_viewer2.common.UiText

sealed class InfoRepoViewEffects {
    data class ShowMessage(val message: UiText) : InfoRepoViewEffects()
    object MoveBack : InfoRepoViewEffects()
}
