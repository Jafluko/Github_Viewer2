package com.nekivai.github_viewer2

import android.app.Application

open class GitApp : Application() {

    val appComponent: AppComponent = DaggerAppComponent.create()

}