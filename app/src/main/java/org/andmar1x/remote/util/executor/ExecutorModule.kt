package org.andmar1x.remote.util.executor

import dagger.Module
import org.andmar1x.remote.common.di.AppScope
import javax.inject.Singleton

@Module
class ExecutorModule {

    @AppScope
    @Singleton
    internal fun provideExecutor() = Executor()

    @AppScope
    @Singleton
    internal fun provideMainThread() = MainThread()
}
