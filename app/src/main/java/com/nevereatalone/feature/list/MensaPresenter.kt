package com.nevereatalone.feature.list

import com.nevereatalone.common.rx.RxDisposables
import com.nevereatalone.common.rx.SingleThreadTransformer
import com.nevereatalone.data.api.User
import com.nevereatalone.data.api.firebase.FirebaseUserService
import com.nevereatalone.data.api.firebase.UserService
import com.nevereatalone.feature.list.interactor.GetMensaList
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MensaPresenter @Inject constructor(
        val view: MensaListContract.View,
        val firebaseUserService: FirebaseUserService,
        val userService: UserService,
        val getMensaList: GetMensaList,
        val singleThreadTransformer: SingleThreadTransformer,
        val rxDisposables: RxDisposables) : MensaListContract.Presenter {


    override fun onAttached() {

        view.showLoading()
        view.hideList()
        view.hideEmptyView()

        rxDisposables.add(
                getMensaList.call()
                        .delay(2, TimeUnit.SECONDS)
                        .compose(singleThreadTransformer.apply())
                        .subscribe({ canteens ->
                            view.hideLoading()
                            with(canteens) {
                                if (size > 0) {
                                    view.showList()
                                    view.loadDataToList(canteens)
                                } else {
                                    view.hideList()
                                    view.showEmptyView()
                                }
                            }

                        }))
    }

    override fun onShown() {
        firebaseUserService.getAuthAnonymous().addOnSuccessListener({
            if (it.additionalUserInfo.isNewUser) {
                val user = User(it.user.uid, "Patrick", 25, "Male")
                userService.createUserProfile(user)
            }
        })
    }

    override fun onDetached() {
        rxDisposables.clear()
    }
}