package com.renovavision.footballapp.teams.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.renovavision.footballapp.domain.entities.Teams.*
import com.renovavision.footballapp.teams.R
import com.renovavision.footballapp.teams.databinding.TeamDetailsViewBinding
import com.squareup.picasso.Picasso

class TeamDetailsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = TeamDetailsViewBinding.inflate(LayoutInflater.from(context), this)

    @get:JvmSynthetic
    var details: Team
        get() = throw UnsupportedOperationException()
        set(value) {
            Picasso.get()
                .load(value.strTeamBadge)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(binding.teamImage)

            binding.teamNameText.text = context.getString(R.string.team, value.strTeam)
            binding.teamLeagueText.text = context.getString(R.string.league, value.strLeague)
            binding.teamStadiumText.text = context.getString(R.string.stadium, value.strStadium)
            binding.teamDescriptionText.text = context.getString(R.string.description, value.strDescriptionEN)
        }
}