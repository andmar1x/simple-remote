package org.andmar1x.remote.ui.splash

import org.andmar1x.remote.R
import org.andmar1x.remote.common.App
import org.andmar1x.remote.common.mvp.BaseActivity
import org.andmar1x.remote.util.navigation.Navigator

class SplashActivity :
        BaseActivity<SplashContract.View, SplashPresenter, SplashComponent>(),
        SplashContract.View {

    override fun createComponent(): SplashComponent = DaggerSplashComponent.builder()
            .appComponent(App.component)
            .build()

    override fun layoutId(): Int = R.layout.activity_splash

    override fun redirectToHome(navigator: Navigator) {
        navigator.openHomeScreen(this)
    }

    override fun redirectToDevices(navigator: Navigator) {
        navigator.openDevicesScreen(this)
    }
}
