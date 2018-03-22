package com.nevereatalone.feature.favorite_list_list


interface FavoriteListContract {

    interface View {
    }

    interface Presenter {
        fun onAttached()
        fun onShown()
        fun onDetached()
    }
}