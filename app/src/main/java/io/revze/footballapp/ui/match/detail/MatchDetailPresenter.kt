package io.revze.footballapp.ui.match.detail

import android.content.Context
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.revze.footballapp.R
import io.revze.footballapp.api.ApiServiceInterface
import io.revze.footballapp.ui.base.Presenter
import java.io.IOException

class MatchDetailPresenter(private var matchDetailView: MatchDetailView?,
                           private var processScheduler: Scheduler = Schedulers.newThread(),
                           private var androidScheduler: Scheduler = AndroidSchedulers.mainThread(),
                           private val apiServiceInterface: ApiServiceInterface = ApiServiceInterface.create()) : Presenter<MatchDetailView> {

    override fun onDetach() {
        matchDetailView = null
    }

    fun getMatchDetail(context: Context, matchId: String) {
        matchDetailView?.showLoader()

        apiServiceInterface.getMatchDetail(matchId)
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe({
                    matchDetailView?.hideLoader()
                    matchDetailView?.onSuccessGetMatchDetail(it.matchDetail[0])
                }, {
                    matchDetailView?.hideLoader()
                    when (it) {
                        is IOException -> matchDetailView?.onFailedGetMatchDetail(context.getString(R.string.network_error))
                        else -> matchDetailView?.onFailedGetMatchDetail(context.getString(R.string.server_error))
                    }
                })
    }

    fun getTeamDetail(teamId: String, type: String) {
        apiServiceInterface.getTeamDetail(teamId)
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe {
                    if (type.equals("home")) matchDetailView?.showHomeTeamLogo(it.teams?.get(0)?.logo)
                    else matchDetailView?.showAwayTeamLogo(it.teams?.get(0)?.logo)
                }
    }
}