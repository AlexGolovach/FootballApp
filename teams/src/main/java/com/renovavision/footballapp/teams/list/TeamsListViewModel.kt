package com.renovavision.footballapp.teams.list

import com.renovavision.footballapp.domain.entities.Teams.*
import com.renovavision.footballapp.domain.usecases.GetTeamsList
import com.renovavision.footballapp.teams.TeamsNavigator
import com.renovavision.footballapp.ui.uni.Action
import com.renovavision.footballapp.ui.uni.AsyncAction
import com.renovavision.footballapp.ui.uni.UniViewModel
import javax.inject.Inject

object LoadTeams : AsyncAction
data class TeamClicked(val team: Team) : AsyncAction

object LoadTeamsStarted : Action
object LoadTeamsFailed : Action
data class LoadTeamsSuccess(val teams: List<Team>) : Action

data class State(
    val teams: List<Team>,
    val isLoading: Boolean,
    val showError: Boolean
)

class TeamsListViewModel @Inject constructor(
    private val useCase: GetTeamsList,
    private val navigator: TeamsNavigator
) : UniViewModel<State>() {

    override fun initialState() = State(emptyList(), isLoading = true, showError = false)

    override fun reduce(prevState: State, action: Action) = when (action) {
        is LoadTeamsStarted -> prevState.copy(isLoading = true)
        is LoadTeamsFailed -> prevState.copy(isLoading = false, showError = true)
        is LoadTeamsSuccess -> prevState.copy(
            teams = action.teams,
            isLoading = false,
            showError = false
        )
        else -> prevState
    }

    override fun async(prevState: State, asyncAction: AsyncAction) {
        when (asyncAction) {
            is TeamClicked -> navigator.navTeamsToTeamDetails(asyncAction.team)
            is LoadTeams -> loadTeamsList(prevState)
        }
    }

    private fun loadTeamsList(prevState: State) {
        if (prevState.teams.isNotEmpty()) {
            dispatch(LoadTeamsSuccess(prevState.teams))
        } else {
            dispatch(LoadTeamsStarted)
            useCase.invoke().sub(
                onError = {
                    dispatch(LoadTeamsFailed)
                }
            ) {
                dispatch(LoadTeamsSuccess(it))
            }
        }
    }
}