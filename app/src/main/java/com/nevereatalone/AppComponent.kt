package com.nevereatalone

import android.app.Application
import com.nevereatalone.data.api.ApiModule
import com.nevereatalone.feature.list.MensaListComponent
import com.nevereatalone.feature.list.MensaListModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class, ApiModule::class))

interface AppComponent {

    fun inject(app: Application)
    fun plus(listModule: MensaListModule): MensaListComponent
}
