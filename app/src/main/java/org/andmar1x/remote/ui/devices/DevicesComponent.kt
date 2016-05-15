package org.andmar1x.remote.ui.devices

import android.content.SharedPreferences
import dagger.Component
import org.andmar1x.remote.Discoverer
import org.andmar1x.remote.DiscovererModule
import org.andmar1x.remote.common.di.ActivityScope
import org.andmar1x.remote.common.di.AppComponent
import org.andmar1x.remote.util.navigation.Navigator

@ActivityScope
@Component(modules = arrayOf(DiscovererModule::class), dependencies = arrayOf(AppComponent::class))
interface DevicesComponent :
        DevicesContract.Component<DevicesContract.View, DevicesPresenter> {

    override fun presenter(): DevicesPresenter

    fun preferences(): SharedPreferences

    fun navigator(): Navigator

    fun discoverer(): Discoverer
}
