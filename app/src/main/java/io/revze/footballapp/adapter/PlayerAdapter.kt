package io.revze.footballapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.revze.footballapp.R
import io.revze.footballapp.model.Player
import io.revze.footballapp.model.Team
import io.revze.footballapp.ui.team.detail.TeamDetailActivity
import io.revze.footballapp.utils.GlideApp
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_row_player.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class PlayerAdapter(private val context: Context, private val players: List<Player>) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row_player, parent, false))
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position])
    }

    override fun getItemCount(): Int = players.size

    inner class PlayerViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(player: Player) {
            GlideApp.with(context).load(player.photo).into(iv_player)
            tv_name.text = player.name
            tv_position.text = player.position
            itemView.onClick {
//                context.startActivity<TeamDetailActivity>(TeamDetailActivity.TEAM_ID to team.id,
//                        TeamDetailActivity.TEAM_NAME to team.name,
//                        TeamDetailActivity.STADIUM to team.stadium,
//                        TeamDetailActivity.FORMED_YEAR to team.formedYear,
//                        TeamDetailActivity.LOGO to team.logo,
//                        TeamDetailActivity.DESCRIPTION to team.description)
            }
        }
    }
}