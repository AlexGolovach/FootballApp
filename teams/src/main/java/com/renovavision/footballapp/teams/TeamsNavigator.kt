package com.renovavision.footballapp.teams

import com.renovavision.footballapp.domain.entities.Event
import com.renovavision.footballapp.domain.entities.Teams.*

interface TeamsNavigator {

    fun navTeamsToTeamDetails(team: Team)

    fun navTeamToMatchDetails(event: Event)
}