package org.andmar1x.remote.ui.home

import org.andmar1x.remote.common.mvp.Contract

interface HomeContract {

    interface View :
            Contract.View {
    }

    interface Presenter<V : View> :
            Contract.Presenter<V> {
    }

    interface Component<V : View, P : Presenter<V>> :
            Contract.Component<V, P>
}
