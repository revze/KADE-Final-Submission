package io.revze.footballapp.ui.match.last

import android.content.Context
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.revze.footballapp.R
import io.revze.footballapp.api.ApiServiceInterface
import io.revze.footballapp.ui.base.Presenter
import java.io.IOException

class LastMatchPresenter(private var lastMatchView: LastMatchView?,
                         private var processScheduler: Scheduler = Schedulers.newThread(),
                         private var androidScheduler: Scheduler = AndroidSchedulers.mainThread(),
                         private val apiServiceInterface: ApiServiceInterface = ApiServiceInterface.create()) : Presenter<LastMatchView> {

    override fun onDetach() {
        lastMatchView = null
    }

    fun getLeague(context: Context) {
        apiServiceInterface.getLeagues()
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe({
                    lastMatchView?.onSuccessGetLeague(it.leagues)
                }, {
                    when (it) {
                        is IOException -> lastMatchView?.onFailedGetLeague(context.getString(R.string.network_error))
                        else -> lastMatchView?.onFailedGetLeague(context.getString(R.string.server_error))
                    }
                })
    }

    fun getLastMatch(context: Context, leagueId: String) {
        lastMatchView?.showLoader()

        apiServiceInterface.getLastMatch(leagueId)
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe({
                    lastMatchView?.hideLoader()
                    lastMatchView?.onSuccessGetLastMatch(it.lastMatch)
                }, {
                    lastMatchView?.hideLoader()
                    when (it) {
                        is IOException -> lastMatchView?.onFailedGetLastMatch(context.getString(R.string.network_error))
                        else -> lastMatchView?.onFailedGetLastMatch(context.getString(R.string.server_error))
                    }
                })
    }
}