package com.nevereatalone.feature.list

import com.nevereatalone.ActivityScope
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MensaListModule::class))
@ActivityScope
interface MensaListComponent {
    fun inject(mensaListActivity: MensaListActivity)
}
