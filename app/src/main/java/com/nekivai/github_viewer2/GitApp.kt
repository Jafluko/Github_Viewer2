package com.nekivai.github_viewer2

import android.app.Application
import com.nekivai.android.ComponentRegistry
import com.nekivai.github_viewer2.data.di.ApiModule
import com.nekivai.github_viewer2.data.di.NetworkProvider
import com.nekivai.github_viewer2.data.di.OtherModule

open class GitApp : Application() {

    //lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        ComponentRegistry.registerPersistentComponent(
            DaggerAppComponent
                .builder()
                .apiModule(ApiModule())
                .networkProvider(NetworkProvider())
                .otherModule(OtherModule())
                .build()
        )
    }
}