package com.nevereatalone.feature.list

import com.nevereatalone.feature.list.interactor.GetMensaList
import com.nevereatalone.feature.list.interactor.GetMensaListImpl
import dagger.Module
import dagger.Provides


@Module
class MensaListModule(val activity: MensaListContract.View) {

    @Provides
    fun provideView() = activity

    @Provides
    fun provideGetMensaList(interactor: GetMensaListImpl): GetMensaList = interactor

    @Provides
    fun providePresenter(present: MensaPresenter): MensaListContract.Presenter = present
}