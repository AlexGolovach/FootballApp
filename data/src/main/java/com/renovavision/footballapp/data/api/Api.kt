package com.renovavision.footballapp.data.api

import com.renovavision.footballapp.data.entities.LastEventsEntity
import com.renovavision.footballapp.data.entities.TeamsEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("search_all_teams.php?l=English%20Premier%20League")
    fun loadTeams(): Single<TeamsEntity>

    @GET("eventslast.php")
    fun loadLastEventsByTeam(@Query("id") teamId: Int): Single<LastEventsEntity>
}