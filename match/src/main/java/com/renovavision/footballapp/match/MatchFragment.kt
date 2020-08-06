package com.renovavision.footballapp.match

import android.os.Bundle
import android.view.View
import com.renovavision.footballapp.domain.entities.Event
import com.renovavision.footballapp.match.databinding.FragmentMatchBinding
import com.renovavision.footballapp.ui.uni.UniFragment
import javax.inject.Inject
import com.renovavision.footballapp.ui.utils.bindingDelegate
import com.renovavision.footballapp.ui.utils.onViewLifecycle

class MatchFragment @Inject constructor() :
    UniFragment(
        R.layout.fragment_match
    ) {

    private val binding by bindingDelegate(FragmentMatchBinding::bind)

    private var event: Event? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        event = arguments?.getSerializable("event")?.let { it as Event }

        onViewLifecycle({ binding.toolbar },
            {
                title = context.getString(R.string.details)
            })

        onViewLifecycle({ binding.errorContainer }, {
            errorMessage = getString(R.string.can_not_load_details)
        })
    }

    override fun onStart() {
        super.onStart()

        event?.let {
            binding.matchDetails.details = it
        }

        if (event != null) {
            binding.matchDetails.visibility = View.VISIBLE
            binding.errorContainer.visibility = View.GONE
            binding.progress.visibility = View.GONE
        } else {
            binding.matchDetails.visibility = View.GONE
            binding.errorContainer.visibility = View.VISIBLE
            binding.progress.visibility = View.GONE
        }
    }
}