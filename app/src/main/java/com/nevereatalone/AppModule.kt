package com.nevereatalone

import android.content.Context
import android.content.res.Resources
import com.nevereatalone.common.AppRouter
import com.nevereatalone.common.rx.*
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton


@Module
class AppModule(val context: Context) {

    @Singleton
    @Provides
    fun provideAppContext(): Context = context

    @Singleton
    @Provides
    fun provideAppRounter(): AppRouter = AppRouter(context)

    @Singleton
    @Provides
    fun provideAppResources(): Resources = context.resources

    @Singleton
    @Provides
    fun provideAppLocale(): Locale = Locale.getDefault()

    @Singleton
    @Provides
    fun provideSchedulerObservableTransformer(transformer: ObservableThreadTransformerImpl): ObservableThreadTransformer = transformer

    @Singleton
    @Provides
    fun provideSchedulerSingleTransformer(transformer: SingleThreadTransformerImpl): SingleThreadTransformer = transformer

    @Singleton
    @Provides
    fun provideSchedulerCompletableTransformer(transformer: CompletableThreadTransformerImpl): CompletableThreadTransformer = transformer

    @Provides
    fun provideRxDisposables(): RxDisposables = CompositeDisposables()

}