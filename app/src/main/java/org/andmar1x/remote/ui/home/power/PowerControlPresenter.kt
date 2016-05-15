package org.andmar1x.remote.ui.home.power

import org.andmar1x.remote.common.mvp.BasePresenter
import javax.inject.Inject

class PowerControlPresenter
@Inject
constructor() :
        BasePresenter<PowerControlContract.View>(),
        PowerControlContract.Presenter<PowerControlContract.View> {

    override fun selectItem(item: Int) {
        throw UnsupportedOperationException()
    }
}
