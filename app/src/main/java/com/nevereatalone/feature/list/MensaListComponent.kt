package com.nevereatalone.feature.list

import com.nevereatalone.FeatureScope
import dagger.Subcomponent

@Subcomponent(modules = [(MensaListModule::class)])
@FeatureScope
interface MensaListComponent {
    fun inject(mensaListActivity: MensaListActivity)
}
