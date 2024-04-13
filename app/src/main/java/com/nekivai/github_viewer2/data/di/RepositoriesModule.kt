package com.nekivai.github_viewer2.data.di

import com.nekivai.github_viewer2.data.repositories.GitRepositoryImpl
import com.nekivai.github_viewer2.domain.repositories.GitRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoriesModule {

    @Binds
    @Singleton
    abstract fun provideGitRepository(repository: GitRepositoryImpl): GitRepository
}