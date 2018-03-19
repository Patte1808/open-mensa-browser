package com.nevereatalone.feature.cafeteria_list

import com.nevereatalone.feature.cafeteria_list.interactor.GetCafeteriaList
import com.nevereatalone.feature.cafeteria_list.interactor.GetCafeteriaListImpl
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