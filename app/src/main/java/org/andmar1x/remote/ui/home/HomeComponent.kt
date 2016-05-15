package org.andmar1x.remote.ui.home

import dagger.Component
import org.andmar1x.remote.common.di.ActivityScope
import org.andmar1x.remote.common.di.AppComponent
import org.andmar1x.remote.util.navigation.Navigator

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class))
interface HomeComponent :
        HomeContract.Component<HomeContract.View, HomePresenter> {

    override fun presenter(): HomePresenter

    fun navigator(): Navigator
}
