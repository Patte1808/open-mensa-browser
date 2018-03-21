package com.nevereatalone.feature.onboarding

interface OnboardingContract {

    interface View {
        
    }

    interface Presenter {
        fun onAttached()
        fun onShown()
        fun onDetached()
        fun setUsername(username: String)
        fun setProfilePicture(profilePicture: String)
        fun getUsername(): String
    }
}