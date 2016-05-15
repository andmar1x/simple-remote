package org.andmar1x.remote

import android.content.SharedPreferences
import android.net.wifi.WifiManager
import dagger.Module
import dagger.Provides
import org.andmar1x.remote.common.di.ActivityScope

@Module
class DiscovererModule {

    @ActivityScope
    @Provides
    internal fun provideDiscoverer(wifiManager: WifiManager, preferences: SharedPreferences): Discoverer = Discoverer(wifiManager, preferences)
}
