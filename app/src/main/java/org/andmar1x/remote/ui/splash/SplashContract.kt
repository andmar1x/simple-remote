package org.andmar1x.remote.ui.splash

import org.andmar1x.remote.common.mvp.Contract
import org.andmar1x.remote.util.navigation.Navigator

interface SplashContract : Contract {

    interface View :
            Contract.View {

        fun redirectToHome(navigator: Navigator)

        fun redirectToDevices(navigator: Navigator)
    }

    interface Presenter<V : View> :
            Contract.Presenter<V> {

        fun redirect()
    }

    interface Component<V : View, P : Presenter<V>> :
            Contract.Component<V, P>
}
