package com.nekivai.github_viewer2.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.nekivai.github_viewer2.AppComponent
import com.nekivai.github_viewer2.GitApp

fun Fragment.showToast(uiText: UiText) {
    when (uiText) {
        is UiText.DynamicString -> showToast(uiText.value)
        is UiText.StringResource -> showToast(uiText.id)
    }
}

fun Fragment.showToast(message: String) {
    context?.showToast(message)
}

fun Fragment.showToast(resId: Int, vararg values: String) {
    val context = context
    if (context != null) {
        context.showToast(context.resources.getString(resId, *values))
    } else {
        val activity = activity ?: return
        activity.showToast(activity.resources.getString(resId, *values))
    }
}

fun Fragment.getAppComponent(): AppComponent =
    (requireActivity().application as GitApp).appComponent

fun FragmentActivity.getAppComponent(): AppComponent =
    (application as GitApp).appComponent