package com.nevereatalone.feature.cafeteria_list

import com.nevereatalone.common.rx.RxDisposables
import com.nevereatalone.common.rx.SingleThreadTransformer
import com.nevereatalone.feature.cafeteria_list.interactor.GetCafeteriaList
import javax.inject.Inject

class CafeteriaListPresenter @Inject constructor(
        val view: CafeteriaListContract.View,
        val getCafeteriaList: GetCafeteriaList,
        val singleThreadTransformer: SingleThreadTransformer,
        val rxDisposables: RxDisposables) : CafeteriaListContract.Presenter {


    override fun onAttached() {

        view.showLoading()
        view.hideList()
        view.hideEmptyView()

        rxDisposables.add(
                getCafeteriaList.call()
                        .compose(singleThreadTransformer.apply())
                        .subscribe({ cafeterias ->
                            view.hideLoading()
                            with(cafeterias) {
                                if (size > 0) {
                                    view.showList()
                                    view.loadDataToList(cafeterias)
                                } else {
                                    view.hideList()
                                    view.showEmptyView()
                                }
                            }

                        }))
    }

    override fun onShown() {

    }

    override fun onDetached() {
        rxDisposables.clear()
    }
}