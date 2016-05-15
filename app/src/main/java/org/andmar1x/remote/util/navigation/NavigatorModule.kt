package org.andmar1x.remote.util.navigation

import dagger.Module
import dagger.Provides
import org.andmar1x.remote.common.di.AppScope

@Module
class NavigatorModule {

    @AppScope
    @Provides
    internal fun provideNavigator(): Navigator = Navigator()
}
