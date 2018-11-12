package io.revze.footballapp.ui.match.search

import android.content.Context
import io.revze.footballapp.api.ApiClient
import io.revze.footballapp.model.League
import io.revze.footballapp.model.NextMatch
import io.revze.footballapp.model.SearchMatch
import io.revze.footballapp.ui.base.Presenter

class SearchMatchPresenter(private var searchMatchView: SearchMatchView?) : Presenter<SearchMatchView> {
    override fun onDetach() {
        searchMatchView = null
    }

    fun searchMatch(context: Context, query: String) {
        searchMatchView?.showLoader()

        ApiClient().searchMatches(context, query, object : ApiClient.SearchMatchCallback {
            override fun onSuccess(matches: List<SearchMatch>?) {
                searchMatchView?.hideLoader()
                searchMatchView?.onSuccessSearchMatch(matches)
            }

            override fun onFailed(message: String) {
                searchMatchView?.hideLoader()
                searchMatchView?.onFailedSearchMatch(message)
            }
        })
    }
}