package com.nevereatalone

import android.app.Application
import com.nevereatalone.data.api.ApiModule
import com.nevereatalone.data.api.firebase.FirebaseModule
import com.nevereatalone.feature.list.MensaListComponent
import com.nevereatalone.feature.list.MensaListModule
import com.nevereatalone.feature.onboarding.OnboardingComponent
import com.nevereatalone.feature.onboarding.OnboardingModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class, ApiModule::class, FirebaseModule::class))

interface AppComponent {

    fun inject(app: Application)

    fun plus(listModule: MensaListModule): MensaListComponent

    fun plus(onboardingModule: OnboardingModule): OnboardingComponent
}
