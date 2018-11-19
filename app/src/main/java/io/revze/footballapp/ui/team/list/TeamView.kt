package io.revze.footballapp.ui.team.list

import io.revze.footballapp.model.League
import io.revze.footballapp.model.Team
import io.revze.footballapp.ui.base.View

interface TeamView : View {
    fun onSuccessGetLeague(leagues: List<League>?)

    fun onFailedGetLeague(message: String)

    fun showLoader()

    fun hideLoader()

    fun onSuccessGetTeam(teams: List<Team>?)

    fun onFailedGetTeam(message: String)
}