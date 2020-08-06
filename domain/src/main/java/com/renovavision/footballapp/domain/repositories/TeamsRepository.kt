package com.renovavision.footballapp.domain.repositories

import com.renovavision.footballapp.domain.entities.Teams.*
import io.reactivex.Single

interface TeamsRepository {

    fun loadTeams(): Single<List<Team>>
}