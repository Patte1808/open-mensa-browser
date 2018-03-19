package com.nevereatalone.feature.profile

import com.nevereatalone.common.rx.RxDisposables
import com.nevereatalone.common.rx.SingleThreadTransformer
import javax.inject.Inject

class ProfilePresenter @Inject constructor(
        val view: ProfileContract.View,
        val singleThreadTransformer: SingleThreadTransformer,
        val rxDisposables: RxDisposables) : ProfileContract.Presenter {


    override fun onAttached() {


    }

    override fun onShown() {

    }

    override fun onDetached() {
        rxDisposables.clear()
    }
}