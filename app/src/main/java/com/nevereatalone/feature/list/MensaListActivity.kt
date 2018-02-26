package com.nevereatalone.feature.list

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

    @Inject
    lateinit var getMensaList: GetMensaList

    @Inject
    lateinit var singleThreadTransformer: SingleThreadTransformer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mensa_list)
        (application as App).appComponent
                .plus(MensaListModule())
                .inject(this)

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
