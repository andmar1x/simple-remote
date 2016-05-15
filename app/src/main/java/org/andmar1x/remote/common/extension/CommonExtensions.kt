package org.andmar1x.remote.common.extension

import android.app.Activity
import android.app.Fragment

fun Fragment.consume(f: () -> Unit): Boolean {
    f()
    return true
}

fun Activity.consume(f: () -> Unit): Boolean {
    f()
    return true
}
