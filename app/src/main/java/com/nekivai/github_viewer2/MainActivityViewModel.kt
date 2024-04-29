package com.nekivai.github_viewer2

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val router: Router
) : ViewModel() {

    /*init {
        viewModelScope.launch {
            delay(1000)
            router.navigateTo(FragmentScreens.getSearchFragment())
        }
    }*/
}