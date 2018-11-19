package io.revze.footballapp.ui.team.detail.player.list

import io.revze.footballapp.model.Player
import io.revze.footballapp.ui.base.View

interface TeamPlayerView : View {
    fun showLoader()

    fun hideLoader()

    fun onSuccessGetPlayer(players: List<Player>?)

    fun onFailedGetPlayer(message: String)
}