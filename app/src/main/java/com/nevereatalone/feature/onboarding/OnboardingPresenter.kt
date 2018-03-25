package com.nevereatalone.feature.onboarding

import com.nevereatalone.common.rx.RxDisposables
import com.nevereatalone.common.rx.SingleThreadTransformer
import com.nevereatalone.data.api.firebase.FirebaseUserService
import com.nevereatalone.data.api.firebase.UserService
import com.nevereatalone.data.local.shared_preferences.SharedPreferencesService
import javax.inject.Inject

class OnboardingPresenter @Inject constructor(
        val view: OnboardingContract.View,
        val firebaseUserService: FirebaseUserService,
        val userService: UserService,
        val singleThreadTransformer: SingleThreadTransformer,
        val rxDisposables: RxDisposables,
        val state: OnboardingState,
        val sharedPreferencesService: SharedPreferencesService) : OnboardingContract.Presenter {

    override fun onAttached() {
        val isAlreadyOnboarded = sharedPreferencesService.getOnboardingStatus()

        if (isAlreadyOnboarded) {
            view.openCanteenList()
        }
    }

    override fun onShown() {

    }

    override fun onDetached() {

    }

    override fun setUsername(username: String) {
        state.username = username
    }

    override fun getUsername() = state.username

    override fun setProfilePicture(profilePicture: String) {
        state.profilePicture = profilePicture
    }

    override fun onFinishOnboarding(state: OnboardingState) {
        firebaseUserService.getAuthAnonymous().addOnSuccessListener {
            state.uid = it.user.uid
            userService.updateProfile(state.toUser())

            sharedPreferencesService.setOnboardingStatus(true)

            view.openCanteenList()
        }
    }
}