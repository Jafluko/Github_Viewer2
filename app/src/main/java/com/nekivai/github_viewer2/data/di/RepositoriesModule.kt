package com.nekivai.github_viewer2.data.di

import com.nekivai.github_viewer2.feature.info_repo.data.InfoRepoRepositoryImpl
import com.nekivai.github_viewer2.feature.info_repo.domain.repositories.InfoRepoRepository
import com.nekivai.github_viewer2.feature.search.data.SearchRepositoryImpl
import com.nekivai.github_viewer2.feature.search.domain.repositories.SearchRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoriesModule {

    @Binds
    @Singleton
    abstract fun provideInfoRepoRepository(repository: InfoRepoRepositoryImpl): InfoRepoRepository

    @Binds
    @Singleton
    abstract fun provideSearchRepository(repository: SearchRepositoryImpl): SearchRepository
}