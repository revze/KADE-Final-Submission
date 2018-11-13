package io.revze.footballapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.revze.footballapp.utils.Helper
import io.revze.footballapp.R
import io.revze.footballapp.model.FavoriteMatch
import io.revze.footballapp.ui.match.detail.MatchDetailActivity
import io.revze.footballapp.model.NextMatch
import io.revze.footballapp.utils.gone
import io.revze.footballapp.utils.visible
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_row_match.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class FavoriteMatchAdapter(private val context: Context, private val favoriteMathces: List<FavoriteMatch>) : RecyclerView.Adapter<FavoriteMatchAdapter.FavoriteMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMatchViewHolder {
        return FavoriteMatchViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row_match, parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteMatchViewHolder, position: Int) {
        holder.bindItem(favoriteMathces[position])
    }

    override fun getItemCount(): Int = favoriteMathces.size

    inner class FavoriteMatchViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(favoriteMatch: FavoriteMatch) {
            val helper = Helper("${favoriteMatch.dateEvent} ${favoriteMatch.timeEvent}")

            tv_date.text = helper.convertDate()
            tv_time.text = helper.convertTime()
            tv_home_team.text = favoriteMatch.homeTeam
            tv_home_score.text = favoriteMatch.homeScore ?: ""
            tv_away_team.text = favoriteMatch.awayTeam
            tv_away_score.text = favoriteMatch.awayScore ?: ""
            iv_add_to_calendar.gone()
            itemView.onClick {
                context.startActivity<MatchDetailActivity>(MatchDetailActivity.MATCH_ID to favoriteMatch.eventId)
            }
        }
    }
}