package com.nekivai.github_viewer2.data.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class OtherModule {

    @Provides
    fun getDispatcherIO() = Dispatchers.IO


    @Provides
    @Singleton
    fun getCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun getNavigationHolder(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    @Singleton
    fun getRouter(cicerone: Cicerone<Router>): Router = cicerone.router

}