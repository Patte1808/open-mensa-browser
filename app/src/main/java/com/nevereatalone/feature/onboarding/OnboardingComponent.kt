package com.nevereatalone.feature.onboarding

import com.nevereatalone.FeatureScope
import dagger.Subcomponent

@Subcomponent(modules = [(OnboardingModule::class)])
@FeatureScope
interface OnboardingComponent {
    fun inject(onboardingView: OnboardingView)
}