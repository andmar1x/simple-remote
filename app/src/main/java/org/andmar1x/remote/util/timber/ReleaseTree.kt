package org.andmar1x.remote.util.timber

import android.util.Log

import timber.log.Timber

class ReleaseTree : Timber.Tree() {

    override fun isLoggable(priority: Int): Boolean {
        return priority != Log.VERBOSE && priority != Log.INFO
    }

    override fun log(priority: Int, tag: String, message: String, t: Throwable?) {
        if (t != null) {
            Log.println(priority, tag, message)
            // Handle custom exceptions.
        }
    }
}