package org.andmar1x.remote.ui.home.power

import dagger.Component
import org.andmar1x.remote.common.di.ActivityScope
import org.andmar1x.remote.common.di.AppComponent

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class))
interface PowerControlComponent :
        PowerControlContract.Component<PowerControlContract.View, PowerControlPresenter> {

    override fun presenter(): PowerControlPresenter
}
