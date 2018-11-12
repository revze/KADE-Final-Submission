package io.revze.footballapp.ui.team.list

import android.content.Context
import io.revze.footballapp.api.ApiClient
import io.revze.footballapp.model.League
import io.revze.footballapp.model.NextMatch
import io.revze.footballapp.model.Team
import io.revze.footballapp.ui.base.Presenter

class TeamPresenter(private var teamView: TeamView?) : Presenter<TeamView> {
    override fun onDetach() {
        teamView = null
    }

    fun getLeague(context: Context) {
        ApiClient().getLeagues(context, object : ApiClient.GetLeagueCallback {
            override fun onSuccess(leagues: List<League>?) {
                teamView?.onSuccessGetLeague(leagues)
            }

            override fun onFailed(message: String) {
                teamView?.onFailedGetLeague(message)
            }
        })
    }

    fun getTeam(context: Context, leagueId: String) {
        teamView?.showLoader()

        ApiClient().getTeams(context, leagueId, object : ApiClient.GetTeamsCallback {
            override fun onSuccess(teams: List<Team>?) {
                teamView?.hideLoader()
                teamView?.onSuccessGetTeam(teams)
            }

            override fun onFailed(message: String) {
                teamView?.hideLoader()
                teamView?.onFailedGetTeam(message)
            }
        })
    }
}