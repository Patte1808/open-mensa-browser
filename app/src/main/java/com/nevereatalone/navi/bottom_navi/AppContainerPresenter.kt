package com.nevereatalone.navi.bottom_navi

import android.view.MenuItem
import com.nevereatalone.R
import com.nevereatalone.common.rx.RxDisposables
import com.nevereatalone.common.rx.SingleThreadTransformer
import javax.inject.Inject

class AppContainerPresenter @Inject constructor(
        val view: AppContainerContract.View,
        val singleThreadTransformer: SingleThreadTransformer,
        val rxDisposables: RxDisposables) : AppContainerContract.Presenter {

    override fun onAttached() {
        view.loadNavigationBar()
    }


    override fun onShown() {
view.setCafeteriaAsDefault()
    }

    override fun onDetached() {
        rxDisposables.clear()
    }

    override fun onNavigationItemClicked(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_cafeteria -> view.goToCafeteriaList()
            R.id.navigation_favorite -> view.goToFavorite()
            R.id.navigation_profile -> view.goToProfile()
        }

        return false
    }
}