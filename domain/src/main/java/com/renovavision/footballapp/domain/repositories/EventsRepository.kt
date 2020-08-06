package com.renovavision.footballapp.domain.repositories

import com.renovavision.footballapp.domain.entities.Event
import io.reactivex.Single

interface EventsRepository {

    fun getLastEvents(teamId: Int): Single<List<Event>>
}