package com.nekivai.github_viewer2.data.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
class OtherModule {

    @Provides
    fun getDispatcherIO() = Dispatchers.IO
}