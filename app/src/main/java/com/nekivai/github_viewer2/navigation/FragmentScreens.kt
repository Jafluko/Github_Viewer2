package com.nekivai.github_viewer2.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.nekivai.github_viewer2.feature.info_repo.presenter.InfoRepoFragment
import com.nekivai.github_viewer2.feature.search.presenter.SearchFragment

object FragmentScreens {

    fun getSearchFragment() = FragmentScreen { SearchFragment.newInstance() }
    fun getInfoRepoFragment() = FragmentScreen { InfoRepoFragment.newInstance() }
}