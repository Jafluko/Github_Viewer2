package com.nekivai.github_viewer2.data.di

import com.nekivai.github_viewer2.AppComponent
import com.nekivai.github_viewer2.MainActivity
import com.nekivai.github_viewer2.core.ViewModelProviderFactory
import dagger.Component

@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [MainActivityModule::class]
)
interface MainActivityComponent : ViewModelProviderFactory {

    fun inject(activity: MainActivity)
}