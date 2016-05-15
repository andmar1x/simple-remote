package org.andmar1x.remote.ui.splash

import android.content.SharedPreferences
import org.andmar1x.remote.common.mvp.BasePresenter
import org.andmar1x.remote.util.executor.MainThread
import org.andmar1x.remote.util.executor.RefRunnable
import org.andmar1x.remote.util.navigation.Navigator
import javax.inject.Inject

class SplashPresenter
@Inject
constructor(private val preferences: SharedPreferences, private val navigator: Navigator, private val mainThread: MainThread) :
        BasePresenter<SplashContract.View>(),
        SplashContract.Presenter<SplashContract.View> {

    val token: Any = Object()

    override fun resumeView() {
        super.resumeView()
        mainThread.post(object : RefRunnable<SplashPresenter>(this) {
            override fun run(obj: SplashPresenter?) {
                obj?.redirect()
            }
        }, token, 1000) // FIXME : Replace with other token
    }

    override fun pauseView() {
        super.pauseView()
        mainThread.clear(token)
    }

    override fun redirect() {
        val view = getView()
        if (preferences.getBoolean("KEY_USE_DEFAULT_CONNECTION", true)) {
            view?.redirectToHome(navigator)
        } else {
            view?.redirectToDevices(navigator)
        }
    }
}
