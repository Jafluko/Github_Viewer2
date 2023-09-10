package com.nekivai.github_viewer2.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class OtherModule {

    @Provides
    fun getDispatcherIO() = Dispatchers.IO
}