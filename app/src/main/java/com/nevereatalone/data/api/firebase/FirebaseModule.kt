package com.nevereatalone.data.api.firebase

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseUserService() = FirebaseUserService()

    @Provides
    @Singleton
    fun provideUserService() = UserService()
}