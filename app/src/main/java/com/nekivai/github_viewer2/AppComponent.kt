package com.nekivai.github_viewer2

import com.nekivai.github_viewer2.data.di.ApiModule
import com.nekivai.github_viewer2.data.di.NetworkProvider
import com.nekivai.github_viewer2.data.di.OtherModule
import com.nekivai.github_viewer2.data.di.RepositoriesModule
import com.nekivai.github_viewer2.presenter.MainActivity
import com.nekivai.github_viewer2.presenter.info.InfoRepoFragment
import com.nekivai.github_viewer2.presenter.search.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        NetworkProvider::class,
        OtherModule::class,
        RepositoriesModule::class,
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: InfoRepoFragment)

    fun inject(fragment: SearchFragment)
}