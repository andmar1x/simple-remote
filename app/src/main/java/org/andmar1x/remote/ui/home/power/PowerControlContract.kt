package org.andmar1x.remote.ui.home.power

import org.andmar1x.remote.common.mvp.Contract

interface PowerControlContract {

    interface View :
            Contract.View {
    }

    interface Presenter<V : View> :
            Contract.Presenter<V> {

        fun selectItem(item: Int)
    }

    interface Component<V : View, P : Presenter<V>> :
            Contract.Component<V, P>
}
