package com.renovavision.footballapp.domain.usecases

import com.renovavision.footballapp.domain.repositories.EventsRepository
import javax.inject.Inject

class GetLastEvents @Inject constructor(private val repository: EventsRepository) {

    fun invoke(teamId: Int) = repository.getLastEvents(teamId)
}