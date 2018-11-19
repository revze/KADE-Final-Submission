package io.revze.footballapp.ui.team.search

import android.content.Context
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.revze.footballapp.R
import io.revze.footballapp.api.ApiServiceInterface
import io.revze.footballapp.ui.base.Presenter
import java.io.IOException

class SearchTeamPresenter(private var searchTeamView: SearchTeamView?,
                          private var processScheduler: Scheduler = Schedulers.newThread(),
                          private var androidScheduler: Scheduler = AndroidSchedulers.mainThread(),
                          private val apiServiceInterface: ApiServiceInterface = ApiServiceInterface.create()) : Presenter<SearchTeamView> {

    override fun onDetach() {
        searchTeamView = null
    }

    fun searchTeam(context: Context, name: String) {
        searchTeamView?.showLoader()

        apiServiceInterface.searchTeam(name)
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe({
                    searchTeamView?.hideLoader()
                    searchTeamView?.onSuccessSearchTeam(it.teams)
                }, {
                    searchTeamView?.hideLoader()
                    when (it) {
                        is IOException -> searchTeamView?.onFailedSearchTeam(context.getString(R.string.network_error))
                        else -> searchTeamView?.onFailedSearchTeam(context.getString(R.string.server_error))
                    }
                })
    }
}