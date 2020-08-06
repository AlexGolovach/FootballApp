package com.renovavision.footballapp.teams.details

import android.view.LayoutInflater
import android.view.ViewGroup
import com.renovavision.footballapp.domain.entities.Event
import com.renovavision.footballapp.teams.databinding.ItemViewMatchBinding
import com.renovavision.footballapp.ui.uni.Dispatch
import com.renovavision.footballapp.ui.utils.BaseAdapter
import com.renovavision.footballapp.ui.utils.BaseViewHolder

class MatchesAdapter(dispatch: Dispatch) :
    BaseAdapter<Event, MatchesAdapter.MatchViewHolder>(dispatch) {

    override fun areItemsTheSame(oldItem: Event, newItem: Event) =
        oldItem.idEvent == oldItem.idEvent

    override fun buildViewHolder(parent: ViewGroup, viewType: Int) = MatchViewHolder(
        ItemViewMatchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    inner class MatchViewHolder(private val binding: ItemViewMatchBinding) :
        BaseViewHolder<Event>(binding.root) {

        override fun onCreate(dispatch: Dispatch) {
            super.onCreate(dispatch)

            itemView.setOnClickListener {
                item.let { dispatch.invoke(MatchClicked(item)) }
            }
        }

        override fun onBind(item: Event) {
            binding.homeTeam.text = item.strHomeTeam
            binding.awayTeam.text = item.strAwayTeam
        }
    }

}