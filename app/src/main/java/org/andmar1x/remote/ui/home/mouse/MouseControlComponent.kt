package org.andmar1x.remote.ui.home.mouse

import dagger.Component
import org.andmar1x.remote.common.di.ActivityScope
import org.andmar1x.remote.common.di.AppComponent
import org.andmar1x.remote.util.navigation.Navigator

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class))
interface MouseControlComponent :
        MouseControlContract.Component<MouseControlContract.View, MouseControlPresenter> {

    override fun presenter(): MouseControlPresenter

    fun navigator(): Navigator
}
