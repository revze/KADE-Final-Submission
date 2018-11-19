package io.revze.footballapp.ui.team.detail.player.list

import android.content.Context
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.revze.footballapp.R
import io.revze.footballapp.api.ApiServiceInterface
import io.revze.footballapp.ui.base.Presenter
import java.io.IOException

class TeamPlayerPresenter(private var view: TeamPlayerView?,
                          private var processScheduler: Scheduler = Schedulers.newThread(),
                          private var androidScheduler: Scheduler = AndroidSchedulers.mainThread(),
                          private val apiServiceInterface: ApiServiceInterface = ApiServiceInterface.create()) : Presenter<TeamPlayerView> {

    override fun onDetach() {
        view = null
    }

    fun getTeam(context: Context, teamId: String) {
        view?.showLoader()

        apiServiceInterface.getPlayer(teamId)
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe({
                    view?.hideLoader()
                    view?.onSuccessGetPlayer(it.players)
                }, {
                    view?.hideLoader()
                    when (it) {
                        is IOException -> view?.onFailedGetPlayer(context.getString(R.string.network_error))
                        else -> view?.onFailedGetPlayer(context.getString(R.string.server_error))
                    }
                })
    }
}