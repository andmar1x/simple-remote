package org.andmar1x.remote.common.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.net.wifi.WifiManager
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @AppScope
    @Provides
    fun provideApplicationContext(): Context = application

    @AppScope
    @Provides
    fun providePreferences(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @AppScope
    @Provides
    fun provideWifiManager(context: Context): WifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
}
