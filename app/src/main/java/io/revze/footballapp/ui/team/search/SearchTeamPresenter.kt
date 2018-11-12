package io.revze.footballapp.ui.team.search

import android.content.Context
import io.revze.footballapp.api.ApiClient
import io.revze.footballapp.model.League
import io.revze.footballapp.model.NextMatch
import io.revze.footballapp.model.SearchMatch
import io.revze.footballapp.model.Team
import io.revze.footballapp.ui.base.Presenter

class SearchTeamPresenter(private var searchTeamView: SearchTeamView?) : Presenter<SearchTeamView> {
    override fun onDetach() {
        searchTeamView = null
    }

    fun searchTeam(context: Context, name: String) {
        searchTeamView?.showLoader()

        ApiClient().searchTeam(context, name, object : ApiClient.SearchTeamCallback {
            override fun onSuccess(team: List<Team>?) {
                searchTeamView?.hideLoader()
                searchTeamView?.onSuccessSearchTeam(team)
            }

            override fun onFailed(message: String) {
                searchTeamView?.hideLoader()
                searchTeamView?.onFailedSearchTeam(message)
            }
        })
    }
}