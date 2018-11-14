package io.revze.footballapp.ui.match.next

import android.content.Context
import io.revze.footballapp.api.ApiClient
import io.revze.footballapp.model.League
import io.revze.footballapp.model.NextMatch
import io.revze.footballapp.ui.base.Presenter

class NextMatchPresenter(private var nextMatchView: NextMatchView?) : Presenter<NextMatchView> {
    override fun onDetach() {
        nextMatchView = null
    }

    private lateinit var leagueCallback: ApiClient.GetLeagueCallback

    fun setLeagueCallback(leagueCallback: ApiClient.GetLeagueCallback) {
        this.leagueCallback = leagueCallback
    }

    fun getLeague(context: Context) {
        ApiClient().getLeagues(context, object : ApiClient.GetLeagueCallback {
            override fun onSuccess(leagues: List<League>?) {
                nextMatchView?.onSuccessGetLeague(leagues)
            }

            override fun onFailed(message: String) {
                nextMatchView?.onFailedGetLeague(message)
            }
        })
    }

    fun getNextMatch(context: Context, leagueId: String) {
        nextMatchView?.showLoader()

        ApiClient().getNextMatches(context, leagueId, object : ApiClient.GetNextMatchCallback {
            override fun onSuccess(nextMatches: List<NextMatch>?) {
                nextMatchView?.hideLoader()
                nextMatchView?.onSuccessGetNextMatch(nextMatches)
            }

            override fun onFailed(message: String) {
                nextMatchView?.hideLoader()
                nextMatchView?.onFailedGetNextMatch(message)
            }
        })
    }
}