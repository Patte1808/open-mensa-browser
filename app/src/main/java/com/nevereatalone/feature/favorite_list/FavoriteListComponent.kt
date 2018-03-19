package com.nevereatalone.feature.favorite_list_list

import com.nevereatalone.FeatureScope
import dagger.Subcomponent

@Subcomponent(modules = [(FavoriteListModule::class)])
@FeatureScope
interface FavoriteListComponent {
    fun inject(favoriteListView: FavoriteListView)
}
