package com.renovavision.footballapp.teams.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.renovavision.footballapp.teams.R
import com.renovavision.footballapp.teams.databinding.FragmentTeamsListBinding
import com.renovavision.footballapp.ui.uni.UniFragment
import com.renovavision.footballapp.ui.utils.bindingDelegate
import com.renovavision.footballapp.ui.utils.onViewLifecycle
import javax.inject.Inject

class TeamsListFragment @Inject constructor(private val viewModelFactory: ViewModelProvider.Factory) :
    UniFragment(
        R.layout.fragment_teams_list
    ) {

    private val viewModel: TeamsListViewModel by viewModels { viewModelFactory }

    private val binding by bindingDelegate(FragmentTeamsListBinding::bind)

    private val teamsAdapter = TeamsAdapter { viewModel.dispatch(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onViewLifecycle({ binding.toolbar },
            {
                title = context.getString(R.string.teams)
            })

        onViewLifecycle({ binding.errorContainer }, {
            errorMessage = getString(R.string.can_not_load_teams)
            clickListener = View.OnClickListener { viewModel.dispatch(LoadTeams) }
        }, {
            clickListener = null
        })

        viewModel.dispatch(LoadTeams)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = teamsAdapter
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.state.sub {
            teamsAdapter.updateItems(it.teams)
            binding.recyclerView.visibility = if (!it.showError) View.VISIBLE else View.GONE
            binding.errorContainer.visibility = if (it.showError) View.VISIBLE else View.GONE
            binding.progress.visibility = if (it.isLoading) View.VISIBLE else View.GONE
        }
    }
}