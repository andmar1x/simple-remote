package org.andmar1x.remote.common

import android.app.Application
import org.andmar1x.remote.BuildConfig
import org.andmar1x.remote.common.di.AppComponent
import org.andmar1x.remote.common.di.AppModule
import org.andmar1x.remote.common.di.DaggerAppComponent
import org.andmar1x.remote.util.navigation.NavigatorModule
import org.andmar1x.remote.util.timber.DebugTree
import org.andmar1x.remote.util.timber.ReleaseTree
import timber.log.Timber

class App :
        Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        Timber.plant(if (BuildConfig.DEBUG) DebugTree() else ReleaseTree())

        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .navigatorModule(NavigatorModule())
                .build()
    }
}
