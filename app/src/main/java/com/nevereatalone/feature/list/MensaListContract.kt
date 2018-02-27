package com.nevereatalone.feature.list

import com.nevereatalone.feature.models.Canteen


interface MensaListContract {

    interface View {
        fun loadDataToList(canteens: List<Canteen>)
        fun showEmptyView()
        fun hideList()
        fun showLoading()
        fun hideLoading()
        fun showList()
    }

    interface Presenter {
        fun onAttached()
        fun onShown()
    }
}