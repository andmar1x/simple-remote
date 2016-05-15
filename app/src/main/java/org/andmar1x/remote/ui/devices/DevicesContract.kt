package org.andmar1x.remote.ui.devices

import org.andmar1x.remote.common.mvp.Contract
import org.andmar1x.remote.model.Device
import org.andmar1x.remote.util.navigation.Navigator

interface DevicesContract {

    interface View :
            Contract.View {

        fun redirectToSplash(navigator: Navigator)

        fun redirectToHome(navigator: Navigator)

        fun startRefreshing()

        fun finishRefreshing()

        fun addDevice(device: Device)

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter<V : View> :
            Contract.Presenter<V> {

        fun redirect()

        fun refresh()
    }

    interface Component<V : View, P : Presenter<V>> :
            Contract.Component<V, P>
}
