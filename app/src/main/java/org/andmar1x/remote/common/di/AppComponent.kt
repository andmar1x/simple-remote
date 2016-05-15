package org.andmar1x.remote.common.di

import android.content.Context
import android.content.SharedPreferences
import android.net.wifi.WifiManager
import dagger.Component
import org.andmar1x.remote.util.executor.Executor
import org.andmar1x.remote.util.executor.ExecutorModule
import org.andmar1x.remote.util.executor.MainThread
import org.andmar1x.remote.util.navigation.Navigator
import org.andmar1x.remote.util.navigation.NavigatorModule

@AppScope
@Component(modules = arrayOf(AppModule::class, NavigatorModule::class, ExecutorModule::class))
interface AppComponent {

    val applicationContext: Context

    val preferences: SharedPreferences

    val navigator: Navigator

    // TODO : MOve from here
    val wifiManager: WifiManager

    val executor: Executor

    val mainThread: MainThread
}
