package com.nevereatalone.feature.list

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.nevereatalone.App
import com.nevereatalone.R
import com.nevereatalone.common.rx.SingleThreadTransformer
import com.nevereatalone.feature.list.interactor.GetMensaList
import com.nevereatalone.feature.list.interactor.MensListAdapter
import com.nevereatalone.feature.models.Canteen
import kotlinx.android.synthetic.main.mensa_list.*
import javax.inject.Inject

class MensaListActivity : AppCompatActivity() {

    val Activity.app: App
        get() = application as App

    @Inject
    lateinit var getMensaList: GetMensaList

    @Inject
    lateinit var singleThreadTransformer: SingleThreadTransformer

    val component by lazy { app.component.plus(MensaListModule(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mensa_list)
        component.inject(this)

        getMensaList.call()
                .compose(singleThreadTransformer.apply())
                .subscribe({ canteens -> initViews(canteens) })
    }

    private fun initViews(canteens: List<Canteen>) {
        list_mensa.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MensListAdapter(canteens)
        }
    }
}
