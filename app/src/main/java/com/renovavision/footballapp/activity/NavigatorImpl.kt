package com.renovavision.footballapp.activity

import androidx.navigation.findNavController
import com.renovavision.footballapp.R
import com.renovavision.footballapp.domain.entities.Event
import com.renovavision.footballapp.domain.entities.Teams.*
import com.renovavision.footballapp.teams.TeamsNavigator
import com.renovavision.footballapp.teams.details.TeamDetailsFragmentDirections
import com.renovavision.footballapp.teams.list.TeamsListFragmentDirections
import com.renovavision.footballapp.ui.navigation.Navigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigatorImpl @Inject constructor() : Navigator, TeamsNavigator {

    private var activity: MainActivity? = null

    fun bind(mainActivity: MainActivity) {
        this.activity = mainActivity
    }

    fun unbind() {
        this.activity = null
    }

    override fun navBack() {
        activity?.apply {
            runOnUiThread {
                findNavController(R.id.navHostFragment).popBackStack()
            }
        }
    }

    override fun navTeamsToTeamDetails(team: Team) {
        activity?.apply {
            runOnUiThread {
                findNavController(R.id.navHostFragment).navigate(
                    TeamsListFragmentDirections.navigateToTeamDetails(team)
                )
            }
        }
    }

    override fun navTeamToMatchDetails(event: Event) {
        activity?.apply {
            runOnUiThread {
                findNavController(R.id.navHostFragment).navigate(
                    TeamDetailsFragmentDirections.navigateToMatchDetails(event)
                )
            }
        }
    }
}