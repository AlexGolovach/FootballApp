package com.renovavision.footballapp.match

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.renovavision.footballapp.domain.entities.Event
import com.renovavision.footballapp.match.databinding.MatchDetailsViewBinding
import com.squareup.picasso.Picasso

class MatchDetailsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = MatchDetailsViewBinding.inflate(LayoutInflater.from(context), this)

    @get:JvmSynthetic
    var details: Event
        get() = throw UnsupportedOperationException()
        set(value) {
            Picasso.get()
                .load(value.strThumb)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(binding.bannerImage)

            showMatchTitle(value)
            showSquad(value)
        }

    private fun showMatchTitle(value: Event) {
        binding.homeTeam.text = value.strHomeTeam
        binding.guestTeam.text = value.strAwayTeam

        if (value.intHomeScore != null && value.intAwayScore != null) {
            binding.score.text = value.getScore()
        }
    }

    private fun showSquad(value: Event) {
        if (value.strHomeLineupGoalkeeper != null) {
            binding.homeMainSquad.text = value.getHomeSquad()
            binding.homeSecondSquad.text = value.strHomeLineupSubstitutes
            binding.guestMainSquad.text = value.getAwaySquad()
            binding.guestSecondSquad.text = value.strAwayLineupSubstitutes
        }
    }
}