package org.andmar1x.remote.util.timber

import android.util.Log

import timber.log.Timber

class DebugTree : Timber.DebugTree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, tag, message, t)

        if (priority == Log.DEBUG || priority == Log.WARN || priority == Log.ERROR) {
            // Handle log messages
        }

        if (t != null) {
            // Handle custom exceptions
        }
    }
}
