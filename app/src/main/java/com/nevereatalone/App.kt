package com.nevereatalone

import android.app.Application
import com.nevereatalone.data.api.ApiModule
import timber.log.BuildConfig
import timber.log.Timber
import javax.inject.Inject
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric


class App : Application() {
    @Inject
    lateinit var app: Application

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this.applicationContext))
                .apiModule(ApiModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initFabric()
        component.inject(this)
    }

    private fun initFabric() {
        if (!com.nevereatalone.BuildConfig.DEBUG)
            Fabric.with(this, Crashlytics())
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}