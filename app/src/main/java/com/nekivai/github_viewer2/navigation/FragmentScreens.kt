package com.nekivai.github_viewer2.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.nekivai.github_viewer2.presenter.info.InfoRepoFragment
import com.nekivai.github_viewer2.presenter.search.SearchFragment

object FragmentScreens {

    fun getSearchFragment() = FragmentScreen { SearchFragment.newInstance() }
    fun getInfoRepoFragment() = FragmentScreen { InfoRepoFragment.newInstance() }
}