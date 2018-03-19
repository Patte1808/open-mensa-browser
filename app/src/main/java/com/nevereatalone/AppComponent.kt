package com.nevereatalone

import android.app.Application
import com.nevereatalone.data.api.ApiModule
import com.nevereatalone.data.api.firebase.FirebaseModule
import com.nevereatalone.navi.bottom_navi.AppContainerComponent
import com.nevereatalone.navi.bottom_navi.AppContainerModule
import com.nevereatalone.feature.list.CafeteriaListComponent
import com.nevereatalone.feature.list.CafeteriaListModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class, ApiModule::class, FirebaseModule::class))

interface AppComponent {

    fun inject(app: Application)

    fun plus(listModule: CafeteriaListModule): CafeteriaListComponent

    fun plus(appModule: AppContainerModule): AppContainerComponent

}
