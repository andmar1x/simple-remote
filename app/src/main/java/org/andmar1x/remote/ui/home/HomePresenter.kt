package org.andmar1x.remote.ui.home

import org.andmar1x.remote.common.mvp.BasePresenter
import javax.inject.Inject

class HomePresenter
@Inject
constructor() :
        BasePresenter<HomeContract.View>(),
        HomeContract.Presenter<HomeContract.View> {

}
