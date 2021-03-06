package io.revze.footballapp.ui.team.search

import io.revze.footballapp.model.Team
import io.revze.footballapp.ui.base.View

interface SearchTeamView : View {
    fun showLoader()

    fun hideLoader()

    fun onSuccessSearchTeam(team: List<Team>?)

    fun onFailedSearchTeam(message: String)
}