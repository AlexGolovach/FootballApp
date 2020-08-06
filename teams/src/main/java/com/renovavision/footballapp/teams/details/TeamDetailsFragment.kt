package com.renovavision.footballapp.teams.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.renovavision.footballapp.domain.entities.Teams.*
import com.renovavision.footballapp.teams.R
import com.renovavision.footballapp.teams.databinding.FragmentTeamDetailsBinding
import com.renovavision.footballapp.teams.list.LoadTeams
import com.renovavision.footballapp.ui.uni.UniFragment
import com.renovavision.footballapp.ui.utils.bindingDelegate
import com.renovavision.footballapp.ui.utils.onViewLifecycle
import javax.inject.Inject

class TeamDetailsFragment @Inject constructor(private val viewModelFactory: ViewModelProvider.Factory) :
    UniFragment(
        R.layout.fragment_team_details
    ) {

    private val viewModel: TeamDetailsViewModel by viewModels { viewModelFactory }

    private val binding by bindingDelegate(FragmentTeamDetailsBinding::bind)

    private val matchesAdapter = MatchesAdapter { viewModel.dispatch(it) }

    private var team: Team? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        team = arguments?.getSerializable("team")?.let { it as Team }

        onViewLifecycle({ binding.toolbar },
            {
                title = context.getString(R.string.team_details)
            })

        onViewLifecycle({ binding.errorContainer }, {
            errorMessage = getString(R.string.can_not_load_details)
            clickListener = View.OnClickListener { viewModel.dispatch(LoadTeams) }
        }, {
            clickListener = null
        })

        team?.let { viewModel.dispatch(LoadMatches(it.idTeam)) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = GridLayoutManager(context, 3, RecyclerView.HORIZONTAL, false)
            adapter = matchesAdapter
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.state.sub {
            matchesAdapter.updateItems(it.events)
            team?.let { details ->
                binding.teamDetails.details = details
            }
            binding.teamDetails.visibility = if (!it.showError) View.VISIBLE else View.GONE
            binding.recyclerView.visibility = if (!it.showError) View.VISIBLE else View.GONE
            binding.errorContainer.visibility = if (it.showError) View.VISIBLE else View.GONE
            binding.progress.visibility = if (it.isLoading) View.VISIBLE else View.GONE
        }
    }
}