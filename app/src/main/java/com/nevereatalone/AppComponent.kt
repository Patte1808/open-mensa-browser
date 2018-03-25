package com.nevereatalone

import android.app.Application
import com.nevereatalone.data.api.ApiModule
import com.nevereatalone.data.api.firebase.FirebaseModule
import com.nevereatalone.feature.cafeteria_list.CafeteriaListComponent
import com.nevereatalone.feature.cafeteria_list.CafeteriaListModule
import com.nevereatalone.feature.favorite_list_list.FavoriteListComponent
import com.nevereatalone.feature.favorite_list_list.FavoriteListModule
import com.nevereatalone.feature.onboarding.OnboardingComponent
import com.nevereatalone.feature.onboarding.OnboardingModule
import com.nevereatalone.feature.profile.ProfileComponent
import com.nevereatalone.feature.profile.ProfileModule
import com.nevereatalone.navi.bottom_navi.AppContainerComponent
import com.nevereatalone.navi.bottom_navi.AppContainerModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class, ApiModule::class, FirebaseModule::class))

interface AppComponent {

    fun inject(app: Application)

    // Feature Scopes

    fun plus(appModule: AppContainerModule): AppContainerComponent
    fun plus(listModule: CafeteriaListModule): CafeteriaListComponent
    fun plus(favoriteListModule: FavoriteListModule): FavoriteListComponent
    fun plus(profileModule: ProfileModule): ProfileComponent
    fun plus(onboardingModule: OnboardingModule): OnboardingComponent
}
