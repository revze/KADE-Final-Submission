package io.revze.footballapp.ui.match.last

import android.content.Context
import io.revze.footballapp.api.ApiClient
import io.revze.footballapp.model.LastMatch
import io.revze.footballapp.model.League
import io.revze.footballapp.ui.base.Presenter

class LastMatchPresenter(private var lastMatchView: LastMatchView?) : Presenter<LastMatchView> {
    override fun onDetach() {
        lastMatchView = null
    }

    fun getLeague(context: Context) {
        ApiClient().getLeagues(context, object : ApiClient.GetLeagueCallback {
            override fun onSuccess(leagues: List<League>?) {
                lastMatchView?.onSuccessGetLeague(leagues)
            }

            override fun onFailed(message: String) {
                lastMatchView?.onFailedGetLeague(message)
            }
        })
    }

    fun getLastMatch(context: Context, leagueId: String) {
        lastMatchView?.showLoader()

        ApiClient().getLastMatches(context, leagueId, object : ApiClient.GetLastMatchCallback {
            override fun onSuccess(lastMatches: List<LastMatch>?) {
                lastMatchView?.hideLoader()
                lastMatchView?.onSuccessGetLastMatch(lastMatches)
            }

            override fun onFailed(message: String) {
                lastMatchView?.hideLoader()
                lastMatchView?.onFailedGetLastMatch(message)
            }
        })
    }
}