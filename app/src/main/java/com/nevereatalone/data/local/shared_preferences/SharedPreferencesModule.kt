package com.nevereatalone.data.local.shared_preferences

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferencesModule {
    @Provides
    @Singleton
    fun provideSharedPreferencesService(context: Context) = SharedPreferencesService(context)
}