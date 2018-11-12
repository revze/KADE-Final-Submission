package io.revze.footballapp.ui.match.search

import io.revze.footballapp.model.League
import io.revze.footballapp.model.NextMatch
import io.revze.footballapp.model.SearchMatch
import io.revze.footballapp.ui.base.View

interface SearchMatchView : View {
    fun showLoader()

    fun hideLoader()

    fun onSuccessSearchMatch(matches: List<SearchMatch>?)

    fun onFailedSearchMatch(message: String)
}