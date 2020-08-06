package com.renovavision.footballapp.teams.details

import com.renovavision.footballapp.domain.entities.Event
import com.renovavision.footballapp.domain.usecases.GetLastEvents
import com.renovavision.footballapp.teams.TeamsNavigator
import com.renovavision.footballapp.ui.uni.Action
import com.renovavision.footballapp.ui.uni.AsyncAction
import com.renovavision.footballapp.ui.uni.UniViewModel
import javax.inject.Inject

data class LoadMatches(val teamId: Int) : AsyncAction
data class MatchClicked(val event: Event) : AsyncAction

object LoadMatchesStarted : Action
object LoadMatchesFailed : Action
data class LoadMatchesSuccess(val events: List<Event>) : Action

data class State(
    val events: List<Event>,
    val isLoading: Boolean,
    val showError: Boolean
)

class TeamDetailsViewModel @Inject constructor(
    private val navigator: TeamsNavigator,
    private val getLastEvents: GetLastEvents
) : UniViewModel<State>() {

    override fun initialState() = State(events = emptyList(), isLoading = true, showError = false)

    override fun reduce(prevState: State, action: Action) = when (action) {
        is LoadMatchesStarted -> prevState.copy(isLoading = true)
        is LoadMatchesFailed -> prevState.copy(isLoading = false, showError = false)
        is LoadMatchesSuccess -> prevState.copy(
            events = action.events,
            isLoading = false,
            showError = false
        )
        else -> prevState
    }

    override fun async(prevState: State, asyncAction: AsyncAction) {
        when (asyncAction) {
            is MatchClicked -> navigator.navTeamToMatchDetails(asyncAction.event)
            is LoadMatches -> loadEvents(asyncAction.teamId)
        }
    }

    private fun loadEvents(teamId: Int) {
        getLastEvents.invoke(teamId).sub(onError = {
            dispatch(LoadMatchesFailed)
        }) {
            dispatch(LoadMatchesSuccess(it))
        }
    }
}