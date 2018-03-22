package com.nevereatalone.navi.bottom_navi

import android.view.MenuItem


interface AppContainerContract {

    interface View {
        fun loadNavigationBar()
        fun goToFavorite(): Boolean
        fun goToProfile(): Boolean
        fun goToCafeteriaList(): Boolean
        fun setCafeteriaAsDefault()
    }

    interface Presenter {
        fun onAttached()
        fun onShown()
        fun onDetached()
        fun onNavigationItemClicked(item: MenuItem): Boolean
    }
}