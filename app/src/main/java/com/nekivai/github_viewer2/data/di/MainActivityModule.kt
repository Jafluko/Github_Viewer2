package com.nekivai.github_viewer2.data.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nekivai.github_viewer2.MainActivityViewModel
import com.nekivai.github_viewer2.core.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainActivityModule {

    @Binds
    @ActivityScope
    fun provideViewModelFactory(viewModelProviderFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @ActivityScope
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun provideMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel
}