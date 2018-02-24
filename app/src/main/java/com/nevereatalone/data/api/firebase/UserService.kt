package com.nevereatalone.data.api.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.nevereatalone.data.api.User

class UserService {
    val databaseRef = FirebaseFirestore.getInstance()

    fun createUserProfile(user: User) {
        Log.d("UserService", "UserID: ${user.uid}")
        databaseRef.collection("users").document(user.uid).set(user)
    }

    fun getUser(userUid: String) = databaseRef.collection("users").document(userUid)
}