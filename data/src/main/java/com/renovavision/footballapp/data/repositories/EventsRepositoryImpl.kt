package com.renovavision.footballapp.data.repositories

import com.renovavision.footballapp.data.api.Api
import com.renovavision.footballapp.data.mapper.eventMapper
import com.renovavision.footballapp.domain.repositories.EventsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventsRepositoryImpl @Inject constructor(private val api: Api) : EventsRepository {

    override fun getLastEvents(teamId: Int) =
        api.loadLastEventsByTeam(teamId).map { events ->
            events.results.map { eventMapper(it) }
        }
}