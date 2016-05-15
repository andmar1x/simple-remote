package org.andmar1x.remote.ui.splash

import android.content.SharedPreferences
import dagger.Component
import org.andmar1x.remote.common.di.ActivityScope
import org.andmar1x.remote.common.di.AppComponent
import org.andmar1x.remote.util.navigation.Navigator

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class))
interface SplashComponent :
        SplashContract.Component<SplashContract.View, SplashPresenter> {

    override fun presenter(): SplashPresenter

    fun preferences(): SharedPreferences

    fun navigator(): Navigator
}
