package com.renovavision.footballapp.activity

import com.renovavision.footballapp.teams.TeamsNavigator
import com.renovavision.footballapp.ui.navigation.Navigator
import dagger.Binds
import dagger.Module

@Module
interface NavigationModule {

    @Binds
    fun provideNavigator(navigator: NavigatorImpl): Navigator

    @Binds
    fun provideTeamsNavigator(navigator: NavigatorImpl): TeamsNavigator
}