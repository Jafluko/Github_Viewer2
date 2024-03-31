package com.nekivai.github_viewer2.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.nekivai.github_viewer2.R
import com.nekivai.github_viewer2.common.getAppComponent

class MainActivity : AppCompatActivity() {

    private var appBarConfiguration: AppBarConfiguration? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        getAppComponent().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navHostFragment =
            this.supportFragmentManager.findFragmentById(R.id.container_fragment) as? NavHostFragment
        val navController = navHostFragment?.navController
        navController?.let { controller ->
            controller.setGraph(R.navigation.nav_graph)
            appBarConfiguration = AppBarConfiguration(controller.graph)
            appBarConfiguration?.let {
                setupActionBarWithNavController(controller, it)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.container_fragment)
        return appBarConfiguration?.let {
            navController.navigateUp(it) || super.onSupportNavigateUp()
        } ?: super.onSupportNavigateUp()
    }
}