package com.nevereatalone.feature.list

import com.nevereatalone.FeatureScope
import dagger.Subcomponent

@Subcomponent(modules = [(CafeteriaListModule::class)])
@FeatureScope
interface CafeteriaListComponent {
    fun inject(cafeteriaListView: CafeteriaListView)
}
