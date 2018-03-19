package com.nevereatalone.feature.profile

import com.nevereatalone.FeatureScope
import dagger.Subcomponent

@Subcomponent(modules = [(ProfileModule::class)])
@FeatureScope
interface ProfileComponent {
    fun inject(profileView: ProfileView)
}
