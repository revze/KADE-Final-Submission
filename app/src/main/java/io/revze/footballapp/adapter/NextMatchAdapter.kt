package io.revze.footballapp.adapter

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.revze.footballapp.utils.Helper
import io.revze.footballapp.R
import io.revze.footballapp.ui.match.detail.MatchDetailActivity
import io.revze.footballapp.model.NextMatch
import io.revze.footballapp.utils.visible
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_row_match.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.util.*

class NextMatchAdapter(private val context: Context, private val nextMathces: List<NextMatch>) : RecyclerView.Adapter<NextMatchAdapter.NextMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextMatchViewHolder {
        return NextMatchViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row_match, parent, false))
    }

    override fun onBindViewHolder(holder: NextMatchViewHolder, position: Int) {
        holder.bindItem(nextMathces[position])
    }

    override fun getItemCount(): Int = nextMathces.size

    inner class NextMatchViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(nextMatch: NextMatch) {
            val helper = Helper("${nextMatch.dateEvent} ${nextMatch.timeEvent}")

            tv_date.text = helper.convertDate()
            tv_time.text = helper.convertTime()
            tv_home_team.text = nextMatch.homeTeam
            tv_home_score.text = ""
            tv_away_team.text = nextMatch.awayTeam
            tv_away_score.text = ""
            iv_add_to_calendar.visible()
            iv_add_to_calendar.onClick {
                val calendarIntent = Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, helper.getTimeInMillis())
                        .putExtra(CalendarContract.Events.TITLE, "${nextMatch.homeTeam} vs ${nextMatch.awayTeam}")
                        .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_TENTATIVE)
                context.startActivity(calendarIntent)
            }
            itemView.onClick {
                context.startActivity<MatchDetailActivity>(MatchDetailActivity.MATCH_ID to nextMatch.eventId)
            }
        }
    }
}