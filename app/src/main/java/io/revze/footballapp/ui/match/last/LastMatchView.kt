package io.revze.footballapp.ui.match.last

import io.revze.footballapp.model.LastMatch
import io.revze.footballapp.model.League
import io.revze.footballapp.ui.base.View

interface LastMatchView : View {
    fun onSuccessGetLeague(leagues: List<League>?)

    fun onFailedGetLeague(message: String)

    fun showLoader()

    fun hideLoader()

    fun onSuccessGetLastMatch(lastMatches: List<LastMatch>?)

    fun onFailedGetLastMatch(message: String)
}