package io.revze.footballapp.ui.team.list

import android.content.Context
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.revze.footballapp.R
import io.revze.footballapp.api.ApiServiceInterface
import io.revze.footballapp.ui.base.Presenter
import java.io.IOException

class TeamPresenter(private var teamView: TeamView?,
                    private var processScheduler: Scheduler = Schedulers.newThread(),
                    private var androidScheduler: Scheduler = AndroidSchedulers.mainThread(),
                    private val apiServiceInterface: ApiServiceInterface = ApiServiceInterface.create()) : Presenter<TeamView> {

    override fun onDetach() {
        teamView = null
    }

    fun getLeague(context: Context) {
        apiServiceInterface.getLeagues()
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe({
                    teamView?.onSuccessGetLeague(it.leagues)
                }, {
                    when (it) {
                        is IOException -> teamView?.onFailedGetLeague(context.getString(R.string.network_error))
                        else -> teamView?.onFailedGetLeague(context.getString(R.string.server_error))
                    }
                })
    }

    fun getTeam(context: Context, leagueId: String) {
        teamView?.showLoader()

        apiServiceInterface.getTeams(leagueId)
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe({
                    teamView?.hideLoader()
                    teamView?.onSuccessGetTeam(it.teams)
                }, {
                    teamView?.hideLoader()
                    when (it) {
                        is IOException -> teamView?.onFailedGetTeam(context.getString(R.string.network_error))
                        else -> teamView?.onFailedGetTeam(context.getString(R.string.server_error))
                    }
                })
    }
}