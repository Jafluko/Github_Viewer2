package com.nekivai.github_viewer2.feature.search.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nekivai.github_viewer2.core.ViewModelFactory
import com.nekivai.github_viewer2.data.di.ViewModelKey
import com.nekivai.github_viewer2.feature.search.presenter.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SearchModule {

    @Binds
    @SearchScope
    fun provideViewModelFactory(viewModelProviderFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @SearchScope
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun provideSearchViewModel(viewModel: SearchViewModel): ViewModel

}