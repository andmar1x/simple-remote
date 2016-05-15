package org.andmar1x.remote.util.executor

import java.lang.ref.WeakReference

abstract class RefRunnable<O>(obj: O) : Runnable {

    private val ref: WeakReference<O>

    init {
        ref = WeakReference(obj)
    }

    abstract fun run(obj: O?)

    override fun run() {
        run(ref.get())
    }
}
