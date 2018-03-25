package com.nevereatalone.data.api.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.nevereatalone.data.api.User

class UserService {
    val databaseRef = FirebaseFirestore.getInstance()

    fun createUserProfile(user: User) {
        Log.d("UserService", "UserID: ${user.uid}")
        databaseRef.collection(USERS_COLLECTION).document(user.uid).set(user)
    }

    fun getUser(userUid: String) = databaseRef.collection("users").document(userUid)

    fun updateProfile(user: User) {
        databaseRef.collection(USERS_COLLECTION).document(user.uid).set(user)
    }

    companion object {
        const val USERS_COLLECTION = "users"
    }
}