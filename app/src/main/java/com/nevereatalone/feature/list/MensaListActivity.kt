package com.nevereatalone.feature.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nevereatalone.App
import com.nevereatalone.R
import com.nevereatalone.common.AppRouter
import javax.inject.Inject

class MensaListActivity : AppCompatActivity() {

    @Inject
    lateinit var router: AppRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        (application as App).appComponent
                .plus(MensaListModule())
                .inject(this)

        // test injected dependency
        router.current()
    }
}
