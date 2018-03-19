package com.nevereatalone.feature.profile

import dagger.Module
import dagger.Provides


@Module
class ProfileModule(val view: ProfileContract.View) {

    @Provides
    fun provideView() = view


    @Provides
    fun providePresenter(present: ProfilePresenter): ProfileContract.Presenter = present
}