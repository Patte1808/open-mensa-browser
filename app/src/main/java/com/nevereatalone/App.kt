package com.nevereatalone

import android.app.Application
import com.nevereatalone.data.api.ApiModule
import timber.log.BuildConfig
import timber.log.Timber
import javax.inject.Inject

class App : Application() {
    @Inject
    lateinit var app : Application

     val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this.applicationContext))
                .apiModule(ApiModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
        component.inject(this)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}