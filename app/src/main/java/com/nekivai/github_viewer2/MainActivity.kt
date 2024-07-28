package com.nekivai.github_viewer2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.nekivai.android.ComponentRegistry
import com.nekivai.android.ComponentRegistry.registerComponent
import com.nekivai.github_viewer2.common.CiceroneNavigator
import com.nekivai.github_viewer2.core.injectViewModel
import com.nekivai.github_viewer2.data.di.DaggerMainActivityComponent
import com.nekivai.github_viewer2.data.di.MainActivityComponent
import com.nekivai.github_viewer2.navigation.FragmentScreens
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val component: MainActivityComponent by registerComponent {
        DaggerMainActivityComponent.builder()
            .appComponent(ComponentRegistry.findComponent(AppComponent::class))
            .build()
    }

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = CiceroneNavigator(this, R.id.container_fragment)

    private val viewModel: MainActivityViewModel by injectViewModel {
        component.provideViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

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