package io.revze.footballapp.ui.team.detail.player

import android.content.Context
import io.revze.footballapp.api.ApiClient
import io.revze.footballapp.model.League
import io.revze.footballapp.model.NextMatch
import io.revze.footballapp.model.Player
import io.revze.footballapp.model.Team
import io.revze.footballapp.ui.base.Presenter

class TeamPlayerPresenter(private var view: TeamPlayerView?) : Presenter<TeamPlayerView> {
    override fun onDetach() {
        view = null
    }

    fun getTeam(context: Context, teamId: String) {
        view?.showLoader()

        ApiClient().getPlayer(context, teamId, object : ApiClient.GetPlayerCallback {
            override fun onSuccess(player: List<Player>?) {
                view?.hideLoader()
                view?.onSuccessGetPlayer(player)
            }

            override fun onFailed(message: String) {
                view?.hideLoader()
                view?.onFailedGetPlayer(message)
            }
        })
    }
}