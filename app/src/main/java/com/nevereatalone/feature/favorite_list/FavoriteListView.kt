package com.nevereatalone.feature.favorite_list_list


import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nevereatalone.App
import com.nevereatalone.R
import com.nevereatalone.inflate
import javax.inject.Inject

class FavoriteListView : Fragment(), FavoriteListContract.View {
    val Activity.app: App
        get() = application as App


    @Inject
    lateinit var presenter: FavoriteListContract.Presenter

    val component by lazy { activity.app.component.plus(FavoriteListModule(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? = container?.inflate(R.layout.favorite_list)


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
}