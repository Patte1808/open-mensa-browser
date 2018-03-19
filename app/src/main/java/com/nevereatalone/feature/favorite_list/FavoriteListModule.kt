package com.nevereatalone.feature.favorite_list_list

import dagger.Module
import dagger.Provides


@Module
class FavoriteListModule(val view: FavoriteListContract.View) {

    @Provides
    fun provideView() = view


    @Provides
    fun providePresenter(present: FavoriteListPresenter): FavoriteListContract.Presenter = present
}