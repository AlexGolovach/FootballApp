package com.renovavision.footballapp.domain.usecases

import com.renovavision.footballapp.domain.repositories.TeamsRepository
import javax.inject.Inject

class GetTeamsList @Inject constructor(private val teamsRepository: TeamsRepository) {

    fun invoke() = teamsRepository.loadTeams()
}