package com.nevereatalone.feature.list

import com.nevereatalone.common.rx.SingleThreadTransformer
import com.nevereatalone.data.api.User
import com.nevereatalone.data.api.firebase.FirebaseUserService
import com.nevereatalone.data.api.firebase.UserService
import com.nevereatalone.feature.list.interactor.GetMensaList
import javax.inject.Inject


class MensaPresenter @Inject constructor(
        val view: MensaListContract.View,
        val firebaseUserService: FirebaseUserService,
        val userService: UserService,
        val getMensaList: GetMensaList,
        val singleThreadTransformer: SingleThreadTransformer) : MensaListContract.Presenter {


    override fun onAttached() {

        getMensaList.call()
                .doOnSubscribe {
                    view.showLoading()
                    view.hideList()
                }
                .compose(singleThreadTransformer.apply())
                .subscribe({ canteens ->
                    view.hideLoading()
                    view.showList()
                    view.loadDataToList(canteens)
                })

    }

    override fun onShown() {
        firebaseUserService.getAuthAnonymous().addOnSuccessListener({
            if (it.additionalUserInfo.isNewUser) {
                val user = User(it.user.uid, "Patrick", 25, "Male")
                userService.createUserProfile(user)
            }
        })
    }
}