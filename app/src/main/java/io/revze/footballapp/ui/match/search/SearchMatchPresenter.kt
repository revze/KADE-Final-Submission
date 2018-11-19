package io.revze.footballapp.ui.match.search

import android.content.Context
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.revze.footballapp.R
import io.revze.footballapp.api.ApiServiceInterface
import io.revze.footballapp.ui.base.Presenter
import java.io.IOException

class SearchMatchPresenter(private var searchMatchView: SearchMatchView?,
                           private var processScheduler: Scheduler = Schedulers.newThread(),
                           private var androidScheduler: Scheduler = AndroidSchedulers.mainThread(),
                           private val apiServiceInterface: ApiServiceInterface = ApiServiceInterface.create()) : Presenter<SearchMatchView> {

    override fun onDetach() {
        searchMatchView = null
    }

    fun searchMatch(context: Context, query: String) {
        searchMatchView?.showLoader()

        apiServiceInterface.searchMatch(query)
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe({
                    searchMatchView?.hideLoader()
                    searchMatchView?.onSuccessSearchMatch(it.match)
                }, {
                    searchMatchView?.hideLoader()
                    when (it) {
                        is IOException -> searchMatchView?.onFailedSearchMatch(context.getString(R.string.network_error))
                        else -> searchMatchView?.onFailedSearchMatch(context.getString(R.string.server_error))
                    }
                })
    }
}