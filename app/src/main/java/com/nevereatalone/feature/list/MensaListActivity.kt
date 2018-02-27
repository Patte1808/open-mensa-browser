package com.nevereatalone.feature.list

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.nevereatalone.App
import com.nevereatalone.R

import com.nevereatalone.common.rx.SingleThreadTransformer
import com.nevereatalone.data.api.User
import com.nevereatalone.data.api.firebase.FirebaseUserService
import com.nevereatalone.data.api.firebase.UserService
import com.nevereatalone.feature.list.interactor.GetMensaList
import com.nevereatalone.feature.list.interactor.MensListAdapter
import com.nevereatalone.feature.models.Canteen
import kotlinx.android.synthetic.main.mensa_list.*
import javax.inject.Inject
import com.nevereatalone.data.api.User
import com.nevereatalone.data.api.firebase.FirebaseUserService
import com.nevereatalone.data.api.firebase.UserService

class MensaListActivity : AppCompatActivity() {

    val Activity.app: App
        get() = application as App

    @Inject
    lateinit var getMensaList: GetMensaList

    @Inject
    lateinit var singleThreadTransformer: SingleThreadTransformer

    @Inject
    lateinit var firebaseUserService: FirebaseUserService

    @Inject
    lateinit var userService: UserService

    val component by lazy { app.component.plus(MensaListModule(this)) }

    @Inject
    lateinit var firebaseUserService: FirebaseUserService

    @Inject
    lateinit var userService: UserService

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

    override fun onStart() {
        super.onStart()

        // I think this does belong in a splash activity, but at this moment, this should be ok
        // There should also happen something if auth isn't succesfully.
        // But I'm lacking MVI knowledge at this moment. We need to discuss it again I think
        firebaseUserService.getAuthAnonymous().addOnSuccessListener({
            if (it.additionalUserInfo.isNewUser) {
                val user = User(it.user.uid, "Patrick", 25, "Male")

                userService.createUserProfile(user)
            }
        })
    }

    override fun onStart() {
        super.onStart()

        // I think this does belong in a splash activity, but at this moment, this should be ok
        // There should also happen something if auth isn't succesfully.
        // But I'm lacking MVI knowledge at this moment. We need to discuss it again I think
        firebaseUserService.getAuthAnonymous().addOnSuccessListener({
            if (it.additionalUserInfo.isNewUser) {
                val user = User(it.user.uid, "Patrick", 25, "Male")

                userService.createUserProfile(user)
            }
        })
    }
}
