package io.revze.footballapp.ui.match.detail

import android.content.Context
import io.revze.footballapp.api.ApiClient
import io.revze.footballapp.model.LastMatch
import io.revze.footballapp.model.League
import io.revze.footballapp.model.MatchDetail
import io.revze.footballapp.model.Team
import io.revze.footballapp.ui.base.Presenter

class MatchDetailPresenter(private var matchDetailView: MatchDetailView?) : Presenter<MatchDetailView> {
    override fun onDetach() {
        matchDetailView = null
    }

    fun getMatchDetail(context: Context, matchId: String) {
        matchDetailView?.showLoader()

        ApiClient().getMatchDetail(context, matchId, object : ApiClient.GetMatchDetailCallback {
            override fun onSuccess(matchDetail: MatchDetail?) {
                matchDetailView?.hideLoader()
                matchDetailView?.onSuccessGetMatchDetail(matchDetail)
            }

            override fun onFailed(message: String) {
                matchDetailView?.hideLoader()
                matchDetailView?.onFailedGetMatchDetail(message)
            }
        })
    }

    fun getTeamDetail(context: Context, teamId: String, type: String) {
        ApiClient().getTeamDetail(context, teamId, object : ApiClient.GetTeamDetailCallback {
            override fun onSuccess(team: Team?) {
                if (type.equals("home")) matchDetailView?.showHomeTeamLogo(team?.logo)
                else matchDetailView?.showAwayTeamLogo(team?.logo)
            }

            override fun onFailed(message: String) {

            }
        })
    }
}