package com.nevereatalone.navi.bottom_navi

import com.nevereatalone.FeatureScope
import dagger.Subcomponent

@Subcomponent(modules = [(AppContainerModule::class)])
@FeatureScope
interface AppContainerComponent {
    fun inject(appViewContainer: AppContainer)
}
