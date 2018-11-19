package io.revze.footballapp.ui.match.next

import io.revze.footballapp.model.League
import io.revze.footballapp.model.NextMatch
import io.revze.footballapp.ui.base.View

interface NextMatchView : View {
    fun onSuccessGetLeague(leagues: List<League>?)

    fun onFailedGetLeague(message: String)

    fun showLoader()

    fun hideLoader()

    fun testKepanggil()

    fun onSuccessGetNextMatch(nextMatches: List<NextMatch>?)

    fun onFailedGetNextMatch(message: String)
}