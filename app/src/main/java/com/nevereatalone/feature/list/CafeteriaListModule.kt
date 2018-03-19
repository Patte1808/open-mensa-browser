package com.nevereatalone.feature.list

import com.nevereatalone.feature.list.interactor.GetCafeteriaList
import com.nevereatalone.feature.list.interactor.GetCafeteriaListImpl
import dagger.Module
import dagger.Provides


@Module
class CafeteriaListModule(val view: CafeteriaListContract.View) {

    @Provides
    fun provideView() = view

    @Provides
    fun provideGetMensaList(interactor: GetCafeteriaListImpl): GetCafeteriaList = interactor

    @Provides
    fun providePresenter(present: CafeteriaListPresenter): CafeteriaListContract.Presenter = present
}