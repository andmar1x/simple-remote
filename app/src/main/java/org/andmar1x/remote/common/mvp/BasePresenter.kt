package org.andmar1x.remote.common.mvp

import java.lang.ref.WeakReference

abstract class BasePresenter<V : Contract.View> :
        Contract.Presenter<V> {

    private var viewRef: WeakReference<V>? = null

    override fun getView(): V? = if (viewRef == null) null else viewRef?.get()

    override fun createView(view: V) {
        viewRef = WeakReference(view)
    }

    override fun destroyView() {
        viewRef?.clear()
        viewRef = null
    }

    override fun resumeView() {
    }

    override fun pauseView() {
    }
}
