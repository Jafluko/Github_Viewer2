package com.nekivai.github_viewer2.common

import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator

open class CiceroneNavigator(activity: FragmentActivity, containerId: Int) :
    AppNavigator(activity, containerId) {

    override fun applyCommands(commands: Array<out Command>) {
        super.applyCommands(commands)
    }

    override fun applyCommand(command: Command) {
        super.applyCommand(command)
    }
}
