package com.nevereatalone.navi.bottom_navi

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nevereatalone.App
import com.nevereatalone.R
import com.nevereatalone.feature.cafeteria_list.CafeteriaListView
import kotlinx.android.synthetic.main.app_container.*
import javax.inject.Inject


class AppContainer : AppCompatActivity(), AppContainerContract.View {
    val Activity.app: App
        get() = application as App

    @Inject
    lateinit var presenter: AppContainerContract.Presenter

    val component by lazy { app.component.plus(AppContainerModule(this)) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_container)
        component.inject(this)
        setSupportActionBar(app_toolbar)
        presenter.onAttached()
    }

    override fun onStart() {
        super.onStart()
        presenter.onShown()
    }

    override fun setCafeteriaAsDefault() {
        navigation_bar.selectedItemId = R.id.navigation_cafeteria
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetached()
    }


    override fun loadNavigationBar() {
        navigation_bar.setOnNavigationItemSelectedListener { item ->
            presenter.onNavigationItemClicked(item)
        }
    }

    override fun goToFavorite(): Boolean {
        supportActionBar?.setTitle(R.string.title_favorite)

        return true
    }

    override fun goToProfile(): Boolean {
        supportActionBar?.setTitle(R.string.title_profile)

        return true
    }

    override fun goToCafeteriaList(): Boolean {
        supportActionBar?.setTitle(R.string.title_cafeteria)
        val ft = supportFragmentManager.beginTransaction()

        ft.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        ft.replace(R.id.app_container, CafeteriaListView())
        ft.addToBackStack(null)
        ft.commit()
        return true
    }
}
