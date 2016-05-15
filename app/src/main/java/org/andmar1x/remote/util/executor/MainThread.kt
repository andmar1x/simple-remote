package org.andmar1x.remote.util.executor

import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import javax.inject.Inject

class MainThread
@Inject
constructor() {

    private val handler = Handler(Looper.getMainLooper())

    fun post(runnable: Runnable, token: Any) {
        post(runnable, token, 0)
    }

    fun post(runnable: Runnable, token: Any, delayMillis: Long) {
        handler.postAtTime(runnable, token, SystemClock.uptimeMillis() + delayMillis)
    }

    fun clear(token: Any) {
        handler.removeCallbacksAndMessages(token)
    }
}
