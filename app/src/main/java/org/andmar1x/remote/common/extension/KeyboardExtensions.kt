package org.andmar1x.remote.common.extension

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Fragment.toggleSoftInput() {
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Fragment.hideSoftInput() {
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(activity.currentFocus.windowToken, 0)
}

fun Activity.toggleSoftInput() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Activity.hideSoftInput() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val windowToken = if (currentFocus != null) currentFocus.windowToken else return
    imm.hideSoftInputFromWindow(windowToken, 0)
}
