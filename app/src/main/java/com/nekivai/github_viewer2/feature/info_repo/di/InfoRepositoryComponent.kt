package com.nekivai.github_viewer2.feature.info_repo.di

import com.nekivai.github_viewer2.AppComponent
import com.nekivai.github_viewer2.feature.info_repo.presenter.InfoRepoFragment
import com.nekivai.github_viewer2.feature.info_repo.presenter.InfoViewModel
import dagger.Component

@InfoRepositoryScope
@Component(
    dependencies = [AppComponent::class]
)
interface InfoRepositoryComponent {

    fun inject(fragment: InfoRepoFragment)

    fun provideInfoViewModelFactory(): InfoViewModel.Factory
}