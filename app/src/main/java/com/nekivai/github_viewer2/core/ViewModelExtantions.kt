package com.nekivai.github_viewer2.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import javax.inject.Inject
import javax.inject.Provider


interface ViewModelProviderFactory {
    fun provideViewModel(): ViewModelProvider.Factory
}

class Factory<T: ViewModel> @Inject constructor(
    savedStateRegistryOwner: SavedStateRegistryOwner,
    private val create: (stateHandle: SavedStateHandle) -> T
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return create.invoke(handle) as T
    }
}

inline fun <reified T : ViewModel> Fragment.assistedViewModel(
    noinline create: (stateHandle: SavedStateHandle) -> T
) = viewModels<T> {
    Factory(this, create)
}

inline fun <reified T : ViewModel> Fragment.injectViewModel(
    noinline create: () -> ViewModelProvider.Factory
) = lazy {
    ViewModelProvider(this, create())[T::class.java]
}

inline fun <reified T : ViewModel> FragmentActivity.injectViewModel(
    noinline create: () -> ViewModelProvider.Factory
) = lazy {
    ViewModelProvider(this, create())[T::class.java]
}


class ViewModelFactory @Inject constructor(
    private var viewModels: Map<Class<out ViewModel>,@JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModelProvider: Provider<ViewModel> = viewModels[modelClass]
            ?: throw IllegalArgumentException(
                "model class "
                    + modelClass
                    + " not found"
            )
        return viewModelProvider.get() as T
    }
}
