package com.nekivai.github_viewer2.feature.search.di

import com.nekivai.github_viewer2.AppComponent
import com.nekivai.github_viewer2.core.ViewModelProviderFactory
import com.nekivai.github_viewer2.feature.search.presenter.SearchFragment
import dagger.Component

@SearchScope
@Component(
    dependencies = [AppComponent::class],
    modules = [SearchModule::class]
)
interface SearchComponent : ViewModelProviderFactory {
    fun inject(fragment: SearchFragment)
}