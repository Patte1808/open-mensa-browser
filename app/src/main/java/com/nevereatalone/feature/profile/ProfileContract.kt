package com.nevereatalone.feature.profile

interface ProfileContract {

    interface View {
    }

    interface Presenter {
        fun onAttached()
        fun onShown()
        fun onDetached()
    }
}