package com.nevereatalone

import android.app.Application
import com.nevereatalone.data.api.ApiModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class, ApiModule::class))

interface AppComponent {

    fun inject(app: Application)

//    fun plus(listModule: ListModule): ListComponent
//
//    fun plus(detailModule: DetailModule): DetailComponent
}
