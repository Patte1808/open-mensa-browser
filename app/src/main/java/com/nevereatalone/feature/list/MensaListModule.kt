package com.nevereatalone.feature.list

import com.nevereatalone.feature.list.interactor.GetMensaList
import com.nevereatalone.feature.list.interactor.GetMensaListImpl
import dagger.Module
import dagger.Provides


@Module
class MensaListModule(val activity: MensaListActivity) {
    @Provides
    fun provideGetMensaList(interactor: GetMensaListImpl): GetMensaList = interactor
}