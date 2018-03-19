package com.nevereatalone.navi.bottom_navi

import dagger.Module
import dagger.Provides


@Module
class AppContainerModule(val view: AppContainerContract.View) {

    @Provides
    fun provideView() = view

    @Provides
    fun providePresenter(present: AppContainerPresenter): AppContainerContract.Presenter = present
}