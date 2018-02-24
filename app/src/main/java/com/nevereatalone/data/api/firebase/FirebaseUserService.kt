package com.nevereatalone.data.api.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseUserService {

    val firebaseAuth = FirebaseAuth.getInstance()

    fun getAuthAnonymous(): Task<AuthResult> = firebaseAuth.signInAnonymously()

    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser

    fun signOut() = firebaseAuth.signOut()
}