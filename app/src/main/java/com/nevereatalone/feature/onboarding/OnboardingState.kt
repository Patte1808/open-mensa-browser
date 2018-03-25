package com.nevereatalone.feature.onboarding

import android.os.Parcelable
import com.nevereatalone.data.api.User
import kotlinx.android.parcel.Parcelize
import javax.inject.Singleton

@Parcelize
@Singleton
data class OnboardingState(var uid: String = "", var username: String = "", var profilePicture: String = "",
                           var tastePictures: MutableList<String> = mutableListOf()) : Parcelable {

    fun toUser() = User(
            uid = this.uid, username = this.username, profilePicture = this.profilePicture,
                taste = this.tastePictures)
}