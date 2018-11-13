package io.revze.footballapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.revze.footballapp.utils.Helper
import io.revze.footballapp.R
import io.revze.footballapp.ui.match.detail.MatchDetailActivity
import io.revze.footballapp.model.LastMatch
import io.revze.footballapp.utils.gone
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_row_match.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class LastMatchAdapter(private val context: Context, private val lastMatches: List<LastMatch>) : RecyclerView.Adapter<LastMatchAdapter.LastMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastMatchViewHolder {
        return LastMatchViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row_match, parent, false))
    }

    override fun onBindViewHolder(holder: LastMatchViewHolder, position: Int) {
        holder.bindItem(lastMatches[position])
    }

    override fun getItemCount(): Int = lastMatches.size

    inner class LastMatchViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(lastMatch: LastMatch) {
            val helper = Helper("${lastMatch.dateEvent} ${lastMatch.timeEvent}")

            tv_date.text = helper.convertDate()
            tv_time.text = helper.convertTime()
            tv_home_team.text = lastMatch.homeTeam
            tv_away_team.text = lastMatch.awayTeam
            tv_home_score.text = lastMatch.homeScore
            tv_away_score.text = lastMatch.awayScore
            iv_add_to_calendar.gone()
            itemView.onClick {
                context.startActivity<MatchDetailActivity>(MatchDetailActivity.MATCH_ID to lastMatch.eventId)
            }
        }
    }
}