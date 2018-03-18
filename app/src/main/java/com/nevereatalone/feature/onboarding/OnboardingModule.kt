package com.nevereatalone.feature.onboarding

import dagger.Module
import dagger.Provides

@Module
class OnboardingModule(val view: OnboardingContract.View) {

    @Provides
    fun provideView() = view

    @Provides
    fun providePresenter(presenter: OnboardingPresenter): OnboardingContract.Presenter = presenter
}