package com.renovavision.footballapp.activity

import androidx.lifecycle.ViewModel
import com.renovavision.footballapp.inject.ViewModelKey
import com.renovavision.footballapp.teams.details.TeamDetailsViewModel
import com.renovavision.footballapp.teams.list.TeamsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(TeamsListViewModel::class)
    fun teamsListViewModel(teamsListViewModel: TeamsListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TeamDetailsViewModel::class)
    fun teamDetailsViewModel(teamDetailsViewModel: TeamDetailsViewModel): ViewModel
}