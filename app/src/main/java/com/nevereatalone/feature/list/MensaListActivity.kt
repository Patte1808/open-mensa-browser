package com.nevereatalone.feature.list

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.nevereatalone.App
import com.nevereatalone.R
import com.nevereatalone.feature.list.interactor.MensListAdapter
import com.nevereatalone.feature.models.Canteen
import kotlinx.android.synthetic.main.mensa_list.*
import javax.inject.Inject


class MensaListActivity : AppCompatActivity(), MensaListContract.View {
    val Activity.app: App
        get() = application as App


    @Inject
    lateinit var presenter: MensaListContract.Presenter

    val component by lazy { app.component.plus(MensaListModule(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mensa_list)
        component.inject(this)
        presenter.onAttached()
    }


    override fun onStart() {
        super.onStart()
        presenter.onShown()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetached()
    }

    override fun loadDataToList(canteens: List<Canteen>) {
        list_mensa.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MensListAdapter(canteens)
        }
    }

    override fun showEmptyView() {
        v_no_mensa.visibility = View.VISIBLE
    }

    override fun hideEmptyView() {
        v_no_mensa.visibility = View.GONE
    }

    override fun showList() {
        list_mensa.visibility = View.VISIBLE
    }

    override fun hideList() {
        list_mensa.visibility = View.GONE
    }

    override fun showLoading() {
        gb_list.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        gb_list.visibility = View.GONE
    }
}
