package org.andmar1x.remote.common.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.andmar1x.remote.common.extension.hideSoftInput

abstract class BaseActivity<V : Contract.View, P : Contract.Presenter<V>, C : Contract.Component<V, P>> :
        AppCompatActivity(),
        Contract.View {

    protected val presenter: P by lazy { component.presenter() }
    protected val component: C by lazy { createComponent() }

    protected abstract fun createComponent(): C

    protected abstract fun layoutId(): Int

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
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
        hideSoftInput()
        presenter.pauseView()
    }
}
