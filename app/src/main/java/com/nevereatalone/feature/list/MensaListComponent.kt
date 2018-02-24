package com.nevereatalone.feature.list

import com.nevereatalone.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(MensaListModule::class))

interface MensaListComponent {
    fun inject(mensaListActivity: MensaListActivity)
}
