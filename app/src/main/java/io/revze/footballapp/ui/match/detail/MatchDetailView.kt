package io.revze.footballapp.ui.match.detail

import io.revze.footballapp.model.LastMatch
import io.revze.footballapp.model.League
import io.revze.footballapp.model.MatchDetail
import io.revze.footballapp.ui.base.View

interface MatchDetailView : View {
    fun showLoader()

    fun hideLoader()

    fun onSuccessGetMatchDetail(matchDetail: MatchDetail?)

    fun onFailedGetMatchDetail(message: String)

    fun showHomeTeamLogo(logo: String?)

    fun showAwayTeamLogo(logo: String?)
}