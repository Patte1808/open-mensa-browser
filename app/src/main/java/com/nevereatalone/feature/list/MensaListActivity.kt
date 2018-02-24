package com.nevereatalone.feature.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nevereatalone.App
import com.nevereatalone.R
import com.nevereatalone.common.AppRouter
import com.nevereatalone.data.api.User
import com.nevereatalone.data.api.firebase.FirebaseUserService
import com.nevereatalone.data.api.firebase.UserService
import javax.inject.Inject



class MensaListActivity : AppCompatActivity() {

    @Inject
    lateinit var router: AppRouter

    @Inject
    lateinit var firebaseUserService: FirebaseUserService

    @Inject
    lateinit var userService: UserService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        (application as App).appComponent
                .plus(MensaListModule())
                .inject(this)

        // test injected dependency
        router.current()
    }

    override fun onStart() {
        super.onStart()

        // I think this does belong in a splash activity, but at this moment, this should be ok
        // There should also happen something if auth isn't succesfully.
        // But I'm lacking MVI knowledge at this moment. We need to discuss it again I think
        firebaseUserService.getAuthAnonymous().addOnSuccessListener({
            if (it.additionalUserInfo.isNewUser) {
                val user = User(it.user.uid, "Patrick", 25, "Male")

                userService.createUserProfile(user)
            }
        })
    }
}
