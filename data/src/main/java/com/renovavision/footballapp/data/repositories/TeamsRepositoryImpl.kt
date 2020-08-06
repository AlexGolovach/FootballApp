package com.renovavision.footballapp.data.repositories

import com.renovavision.footballapp.data.api.Api

import com.renovavision.footballapp.data.mapper.teamMapper
import com.renovavision.footballapp.domain.entities.Teams.*
import com.renovavision.footballapp.domain.repositories.TeamsRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TeamsRepositoryImpl @Inject constructor(private val api: Api) : TeamsRepository {

    private var teamsCache: List<Team> = emptyList()

    override fun loadTeams(): Single<List<Team>> =
        if (teamsCache.isNotEmpty()) Single.just(teamsCache) else refreshTeamsSingle()

    private fun refreshTeamsSingle(): Single<List<Team>> =
        api.loadTeams().map { list ->
            list.teams.map { teamMapper(it) }
        }.doOnSuccess {
            teamsCache = it
        }
}