package io.revze.footballapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.revze.footballapp.R
import io.revze.footballapp.model.FavoriteTeam
import io.revze.footballapp.model.Team
import io.revze.footballapp.ui.team.detail.TeamDetailActivity
import io.revze.footballapp.utils.GlideApp
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_row_team.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class FavoriteTeamAdapter(private val context: Context, private val favoriteTeams: List<FavoriteTeam>) : RecyclerView.Adapter<FavoriteTeamAdapter.FavoriteTeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamViewHolder {
        return FavoriteTeamViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row_team, parent, false))
    }

    override fun onBindViewHolder(holder: FavoriteTeamViewHolder, position: Int) {
        holder.bindItem(favoriteTeams[position])
    }

    override fun getItemCount(): Int = favoriteTeams.size

    inner class FavoriteTeamViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(favoriteTeam: FavoriteTeam) {
            tv_name.text = favoriteTeam.name
            GlideApp.with(context).load(favoriteTeam.logo).into(iv_logo)
            itemView.onClick {
                context.startActivity<TeamDetailActivity>(TeamDetailActivity.TEAM_ID to favoriteTeam.id,
                        TeamDetailActivity.TEAM_NAME to favoriteTeam.name,
                        TeamDetailActivity.STADIUM to favoriteTeam.stadium,
                        TeamDetailActivity.FORMED_YEAR to favoriteTeam.formedYear,
                        TeamDetailActivity.LOGO to favoriteTeam.logo,
                        TeamDetailActivity.DESCRIPTION to favoriteTeam.description)
            }
        }
    }
}