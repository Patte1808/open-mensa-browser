package com.nevereatalone.feature.list

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nevereatalone.App
import com.nevereatalone.R
import com.nevereatalone.feature.list.interactor.MensListAdapter
import com.nevereatalone.feature.models.Canteen
import com.nevereatalone.inflate
import kotlinx.android.synthetic.main.cafeteria_list.*
import javax.inject.Inject

class CafeteriaListView : Fragment(), CafeteriaListContract.View {
    val Activity.app: App
        get() = application as App


    @Inject
    lateinit var presenter: CafeteriaListContract.Presenter

    val component by lazy { activity.app.component.plus(CafeteriaListModule(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = container?.inflate(R.layout.cafeteria_list)


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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