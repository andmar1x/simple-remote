package org.andmar1x.remote.ui.devices

import android.content.SharedPreferences
import org.andmar1x.remote.Discoverer
import org.andmar1x.remote.common.mvp.BasePresenter
import org.andmar1x.remote.model.Device
import org.andmar1x.remote.util.executor.MainThread
import org.andmar1x.remote.util.executor.RefRunnable
import org.andmar1x.remote.util.navigation.Navigator
import javax.inject.Inject

class DevicesPresenter
@Inject
constructor(private val preferences: SharedPreferences, private val navigator: Navigator, private val discoverer: Discoverer, private val mainThread: MainThread) :
        BasePresenter<DevicesContract.View>(),
        DevicesContract.Presenter<DevicesContract.View>,
        Discoverer.Listener {

    override fun createView(view: DevicesContract.View) {
        super.createView(view)
    }

    override fun destroyView() {
        super.destroyView()
    }

    override fun resumeView() {
        super.resumeView()
        refresh()
    }

    override fun pauseView() {
        super.pauseView()
        discoverer.stop()
    }

    override fun refresh() {
        discoverer.start(this)
    }

    override fun redirect() {
        mainThread.post(object : RefRunnable<DevicesPresenter>(this) {
            override fun run(obj: DevicesPresenter?) {
                val view = obj?.getView()
                view?.redirectToHome(navigator)
            }
        }, this)
    }

    override fun onDiscoverStarted() {
        mainThread.post(object : RefRunnable<DevicesPresenter>(this) {
            override fun run(obj: DevicesPresenter?) {
                val view = obj?.getView()
                view?.showProgress()
            }
        }, this)
    }

    override fun onDeviceFound(device: Device) {
        mainThread.post(object : RefRunnable<DevicesPresenter>(this) {
            override fun run(obj: DevicesPresenter?) {
                val view = obj?.getView()
                view?.addDevice(device)
            }
        }, this)
    }

    override fun onDiscoverFinished() {
        mainThread.post(object : RefRunnable<DevicesPresenter>(this) {
            override fun run(obj: DevicesPresenter?) {
                val view = obj?.getView()
                view?.hideProgress()
            }
        }, this)
    }
}
