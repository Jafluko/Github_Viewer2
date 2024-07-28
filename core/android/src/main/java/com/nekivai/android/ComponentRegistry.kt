package com.nekivai.android

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.reflect.KClass

object ComponentRegistry {

    private val components: MutableMap<Any, LifecycleOwner?> = mutableMapOf()

    private fun addComponent(component: Any, lifecycleOwner: LifecycleOwner) {
        val observer = object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                components.remove(component)
                lifecycleOwner.lifecycle.removeObserver(this)
                super.onDestroy(owner)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        components[component] = lifecycleOwner
    }

    fun LifecycleOwner.addComponent(component: Any) {
        addComponent(component, this@addComponent)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> findComponent(clazz: KClass<T>): T {
        val keys = components.keys
        val component = keys.find { clazz.isInstance(it) }
        return component as T
    }

    fun <T : Any> LifecycleOwner.registerComponent(get: () -> T): Lazy<T> = lazy {
        val component = get()
        addComponent(component)
        component
    }

    fun <T : Any> registerPersistentComponent(component: T) {
        components[component] = null
    }

}