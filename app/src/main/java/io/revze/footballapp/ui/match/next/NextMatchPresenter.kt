package io.revze.footballapp.ui.match.next

import android.content.Context
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.revze.footballapp.R
import io.revze.footballapp.api.ApiServiceInterface
import io.revze.footballapp.ui.base.Presenter
import java.io.IOException

class NextMatchPresenter(private var nextMatchView: NextMatchView?,
                         private var processScheduler: Scheduler = Schedulers.newThread(),
                         private var androidScheduler: Scheduler = AndroidSchedulers.mainThread(),
                         private val apiServiceInterface: ApiServiceInterface = ApiServiceInterface.create()) : Presenter<NextMatchView> {

    override fun onDetach() {
        nextMatchView = null
    }

    fun getLeague(context: Context) {
        apiServiceInterface.getLeagues()
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe({
                    nextMatchView?.onSuccessGetLeague(it.leagues)
                }, {
                    when (it) {
                        is IOException -> nextMatchView?.onFailedGetLeague(context.getString(R.string.network_error))
                        else -> nextMatchView?.onFailedGetLeague(context.getString(R.string.server_error))
                    }
                })
    }

    fun getNextMatch(context: Context, leagueId: String) {
        nextMatchView?.showLoader()

        apiServiceInterface.getNextMatch(leagueId)
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe(
                        {
                            nextMatchView?.hideLoader()
                            nextMatchView?.onSuccessGetNextMatch(it.nextMatch)
                        },
                        {
                            nextMatchView?.hideLoader()
                            when (it) {
                                is IOException -> nextMatchView?.onFailedGetNextMatch(context.getString(R.string.network_error))
                                else -> nextMatchView?.onFailedGetNextMatch(context.getString(R.string.server_error))
                            }
                        })
    }
}