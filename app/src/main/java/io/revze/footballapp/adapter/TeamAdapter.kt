package io.revze.footballapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.revze.footballapp.R
import io.revze.footballapp.model.Team
import io.revze.footballapp.ui.team.detail.TeamDetailActivity
import io.revze.footballapp.utils.GlideApp
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_row_team.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class TeamAdapter(private val context: Context, private val teams: List<Team>) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row_team, parent, false))
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position])
    }

    override fun getItemCount(): Int = teams.size

    inner class TeamViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(team: Team) {
            tv_name.text = team.name
            GlideApp.with(context).load(team.logo).into(iv_logo)
            itemView.onClick {
                context.startActivity<TeamDetailActivity>(TeamDetailActivity.TEAM_ID to team.id,
                        TeamDetailActivity.TEAM_NAME to team.name,
                        TeamDetailActivity.STADIUM to team.stadium,
                        TeamDetailActivity.FORMED_YEAR to team.formedYear,
                        TeamDetailActivity.LOGO to team.logo,
                        TeamDetailActivity.DESCRIPTION to team.description)
            }
        }
    }
}