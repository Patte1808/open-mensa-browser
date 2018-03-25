package com.nevereatalone.data.api.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.nevereatalone.data.api.User

class UserService {
    val databaseRef = FirebaseFirestore.getInstance()

    fun updateProfile(user: User) {
        databaseRef.collection(USERS_COLLECTION).document(user.uid).set(user)
    }

    companion object {
        const val USERS_COLLECTION = "users"
    }
}