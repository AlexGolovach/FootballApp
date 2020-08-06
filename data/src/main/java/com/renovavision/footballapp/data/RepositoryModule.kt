package com.renovavision.footballapp.data

import com.renovavision.footballapp.data.repositories.EventsRepositoryImpl
import com.renovavision.footballapp.data.repositories.TeamsRepositoryImpl
import com.renovavision.footballapp.domain.repositories.EventsRepository
import com.renovavision.footballapp.domain.repositories.TeamsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun provideTeamsRepository(teamsRepositoryImpl: TeamsRepositoryImpl): TeamsRepository

    @Binds
    fun provideEventsRepository(eventsRepositoryImpl: EventsRepositoryImpl): EventsRepository
}