package com.nekivai.github_viewer2.common

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Context.showToast(uiText: UiText) {
    when (uiText) {
        is UiText.DynamicString -> showToast(message = uiText.value)
        is UiText.StringResource -> showToast(resId = uiText.id)
    }
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showToast(@StringRes resId: Int) {
    Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show()
}