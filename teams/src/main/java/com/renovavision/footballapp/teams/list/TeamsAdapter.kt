package com.renovavision.footballapp.teams.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.renovavision.footballapp.domain.entities.Teams.*
import com.renovavision.footballapp.teams.R
import com.renovavision.footballapp.teams.databinding.ItemViewTeamBinding
import com.renovavision.footballapp.ui.uni.Dispatch
import com.renovavision.footballapp.ui.utils.BaseAdapter
import com.renovavision.footballapp.ui.utils.BaseViewHolder
import com.squareup.picasso.Picasso

class TeamsAdapter(dispatch: Dispatch) : BaseAdapter<Team, TeamsAdapter.TeamViewHolder>(dispatch) {

    override fun areItemsTheSame(oldItem: Team, newItem: Team) = oldItem.idTeam == oldItem.idTeam

    override fun buildViewHolder(parent: ViewGroup, viewType: Int) = TeamViewHolder(
        ItemViewTeamBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    inner class TeamViewHolder(private val binding: ItemViewTeamBinding) :
        BaseViewHolder<Team>(binding.root) {

        override fun onCreate(dispatch: Dispatch) {
            super.onCreate(dispatch)

            itemView.setOnClickListener {
                item.let { dispatch.invoke(TeamClicked(item)) }
            }
        }

        override fun onBind(item: Team) {
            binding.teamName.text = item.strTeam
            Picasso.get()
                .load(item.strTeamBadge)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(binding.teamImage)
        }
    }
}