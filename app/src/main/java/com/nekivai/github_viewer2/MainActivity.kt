package com.nekivai.github_viewer2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.nekivai.github_viewer2.common.CiceroneNavigator
import com.nekivai.github_viewer2.common.getAppComponent
import com.nekivai.github_viewer2.navigation.FragmentScreens
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = CiceroneNavigator(this, R.id.container_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        getAppComponent().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        router.navigateTo(FragmentScreens.getSearchFragment())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}