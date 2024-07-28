package com.nekivai.github_viewer2

import android.app.Application
import com.nekivai.android.ComponentRegistry

open class GitApp : Application() {

    //lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        ComponentRegistry.registerPersistentComponent(
            DaggerAppComponent
                .factory()
                .create()
        )
    }
}