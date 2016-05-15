package org.andmar1x.remote.ui.home.mouse

import org.andmar1x.remote.common.mvp.BasePresenter
import javax.inject.Inject

class MouseControlPresenter
@Inject
constructor() :
        BasePresenter<MouseControlContract.View>(),
        MouseControlContract.Presenter<MouseControlContract.View> {

    override fun toggleKeyboard(show: Boolean) {
        getView()?.toggleKeyboard(show)
    }

    override fun leftButtonClick() {
        throw UnsupportedOperationException()
    }

    override fun rightButtonClick() {
        throw UnsupportedOperationException()
    }
}
