package com.nevereatalone.feature.onboarding

import com.nevereatalone.common.rx.RxDisposables
import com.nevereatalone.common.rx.SingleThreadTransformer
import com.nevereatalone.data.api.firebase.FirebaseUserService
import com.nevereatalone.data.api.firebase.UserService
import javax.inject.Inject

class OnboardingPresenter @Inject constructor(
        val view: OnboardingContract.View,
        val firebaseUserService: FirebaseUserService,
        val userService: UserService,
        val singleThreadTransformer: SingleThreadTransformer,
        val rxDisposables: RxDisposables) : OnboardingContract.Presenter {

    val onboardingState = OnboardingState()

    override fun onAttached() {

    }

    override fun onShown() {

    }

    override fun onDetached() {

    }

    override fun setUsername(username: String) {
        onboardingState.username = username
    }

    override fun getUsername() = onboardingState.username

    override fun setProfilePicture(profilePicture: String) {
        onboardingState.profilePicture = profilePicture
    }
}