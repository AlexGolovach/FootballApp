package com.renovavision.footballapp.activity

import androidx.fragment.app.Fragment
import com.renovavision.footballapp.inject.FragmentKey
import com.renovavision.footballapp.match.MatchFragment
import com.renovavision.footballapp.teams.details.TeamDetailsFragment
import com.renovavision.footballapp.teams.list.TeamsListFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FragmentsModule {

    @Binds
    @IntoMap
    @FragmentKey(TeamsListFragment::class)
    fun teamsListFragment(teamsListFragment: TeamsListFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(TeamDetailsFragment::class)
    fun teamDetailsFragment(teamDetailsFragment: TeamDetailsFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(MatchFragment::class)
    fun matchFragment(matchFragment: MatchFragment): Fragment
}