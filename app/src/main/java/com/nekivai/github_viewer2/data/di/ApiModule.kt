package com.nekivai.github_viewer2.data.di

import com.nekivai.github_viewer2.data.source.network.GitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideApi(retrofit: Retrofit): GitApi = retrofit.create(GitApi::class.java)
}