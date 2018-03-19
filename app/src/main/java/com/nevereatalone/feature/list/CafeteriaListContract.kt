package com.nevereatalone.feature.list

import com.nevereatalone.feature.models.Canteen


interface CafeteriaListContract {

    interface View {
        fun loadDataToList(canteens: List<Canteen>)
        fun showEmptyView()
        fun hideList()
        fun showLoading()
        fun hideLoading()
        fun showList()
        fun hideEmptyView()
    }

    interface Presenter {
        fun onAttached()
        fun onShown()
        fun onDetached()
    }
}