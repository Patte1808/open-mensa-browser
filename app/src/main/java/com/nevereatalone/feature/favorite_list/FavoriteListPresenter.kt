package com.nevereatalone.feature.favorite_list_list

import com.nevereatalone.common.rx.RxDisposables
import com.nevereatalone.common.rx.SingleThreadTransformer
import javax.inject.Inject

class FavoriteListPresenter @Inject constructor(
        val view: FavoriteListContract.View,
        val singleThreadTransformer: SingleThreadTransformer,
        val rxDisposables: RxDisposables) : FavoriteListContract.Presenter {


    override fun onAttached() {


    }

    override fun onShown() {

    }

    override fun onDetached() {
        rxDisposables.clear()
    }
}