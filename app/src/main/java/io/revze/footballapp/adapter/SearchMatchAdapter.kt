package io.revze.footballapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.revze.footballapp.R
import io.revze.footballapp.model.SearchMatch
import io.revze.footballapp.ui.match.detail.MatchDetailActivity
import io.revze.footballapp.utils.Helper
import io.revze.footballapp.utils.gone
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_row_match.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class SearchMatchAdapter(private val context: Context, private val searchMathces: List<SearchMatch>) : RecyclerView.Adapter<SearchMatchAdapter.SearchMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMatchViewHolder {
        return SearchMatchViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row_match, parent, false))
    }

    override fun onBindViewHolder(holder: SearchMatchViewHolder, position: Int) {
        holder.bindItem(searchMathces[position])
    }

    override fun getItemCount(): Int = searchMathces.size

    inner class SearchMatchViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(searchMatch: SearchMatch) {
            val helper = Helper("${searchMatch.dateEvent} ${searchMatch.timeEvent}")

            tv_date.text = helper.convertDate()
            tv_time.text = helper.convertTime()
            tv_home_team.text = searchMatch.homeTeam
            tv_home_score.text = searchMatch.homeScore ?: ""
            tv_away_team.text = searchMatch.awayTeam
            tv_away_score.text = searchMatch.awayScore ?: ""
            iv_add_to_calendar.gone()
            itemView.onClick {
                context.startActivity<MatchDetailActivity>(MatchDetailActivity.MATCH_ID to searchMatch.eventId)
            }
        }
    }
}