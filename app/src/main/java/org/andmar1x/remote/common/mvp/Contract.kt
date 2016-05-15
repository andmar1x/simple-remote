package org.andmar1x.remote.common.mvp

interface Contract {

    interface View

    interface Presenter<V : View> {

        fun getView(): V?

        fun createView(view: V)

        fun destroyView()

        fun resumeView()

        fun pauseView()
    }

    interface Component<V : View, P :
    Presenter<V>> {

        fun presenter(): P
    }
}
