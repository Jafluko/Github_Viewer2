package com.nekivai.github_viewer2

import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.nekivai.github_viewer2.data.di.ApiModule
import com.nekivai.github_viewer2.data.di.NetworkProvider
import com.nekivai.github_viewer2.data.di.OtherModule
import com.nekivai.github_viewer2.data.di.RepositoriesModule
import com.nekivai.github_viewer2.feature.info_repo.domain.repositories.InfoRepoRepository
import com.nekivai.github_viewer2.feature.search.domain.repositories.SearchRepository
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher
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

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

    fun provideDispatcherIO(): CoroutineDispatcher
    fun provideRouter(): Router
    fun provideNavigatorHolder(): NavigatorHolder

    fun provideSearchRepository(): SearchRepository
    fun provideInfoRepoRepository(): InfoRepoRepository
}