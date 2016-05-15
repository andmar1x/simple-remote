package org.andmar1x.remote.ui.home.mouse

import org.andmar1x.remote.common.mvp.Contract

interface MouseControlContract {

    interface View :
            Contract.View {

        fun toggleKeyboard(show: Boolean)
    }

    interface Presenter<V : View> :
            Contract.Presenter<V> {

        fun leftButtonClick()

        fun rightButtonClick()

        fun toggleKeyboard(show: Boolean)
    }

    interface Component<V : View, P : Presenter<V>> :
            Contract.Component<V, P>
}
