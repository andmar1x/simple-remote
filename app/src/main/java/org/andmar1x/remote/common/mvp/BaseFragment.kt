package org.andmar1x.remote.common.mvp

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment<V : Contract.View, P : Contract.Presenter<V>, C : Contract.Component<V, P>> :
        Fragment(),
        Contract.View {

    protected val presenter: P by lazy { component.presenter() }
    protected val component: C by lazy { createComponent() }

    protected abstract fun createComponent(): C

    protected abstract fun layoutId(): Int

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(layoutId(), container, false)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.createView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroyView()
    }

    override fun onResume() {
        super.onResume()
        presenter.resumeView()
    }

    override fun onPause() {
        super.onPause()
        presenter.pauseView()
    }
}
