package org.andmar1x.remote.util.executor

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class Executor
@Inject
constructor() {

    private val threadPoolExecutor: ThreadPoolExecutor

    init {
        threadPoolExecutor = ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
                TIME_UNIT, WORK_QUEUE)
    }

    fun run(runnable: Runnable) {
        threadPoolExecutor.submit(runnable)
    }

    companion object {

        private val CORE_POOL_SIZE = 3
        private val MAX_POOL_SIZE = 5
        private val KEEP_ALIVE_TIME = 120L

        private val TIME_UNIT = TimeUnit.SECONDS

        private val WORK_QUEUE = LinkedBlockingQueue<Runnable>()
    }
}
