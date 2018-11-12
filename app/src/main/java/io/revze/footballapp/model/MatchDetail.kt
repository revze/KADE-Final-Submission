package io.revze.footballapp.model

import com.google.gson.annotations.SerializedName

class MatchDetail(
        @SerializedName("dateEvent")
        var dateEvent: String,

        @SerializedName("strTime")
        var timeEvent: String,

        @SerializedName("strHomeTeam")
        var homeTeam: String,

        @SerializedName("strAwayTeam")
        var awayTeam: String,

        @SerializedName("intHomeScore")
        var homeScore: String?,

        @SerializedName("intAwayScore")
        var awayScore: String?,

        @SerializedName("strHomeGoalDetails")
        var homeGoalDetails: String?,

        @SerializedName("strAwayGoalDetails")
        var awayGoalDetails: String?,

        @SerializedName("intHomeShots")
        var homeShots: String?,

        @SerializedName("intAwayShots")
        var awayShots: String?,

        @SerializedName("strHomeLineupGoalkeeper")
        var homeLineupGoalkeeper: String?,

        @SerializedName("strHomeLineupDefense")
        var homeLineupDefense: String?,

        @SerializedName("strHomeLineupMidfield")
        var homeLineupMidfield: String?,

        @SerializedName("strHomeLineupForward")
        var homeLineupForward: String?,

        @SerializedName("strHomeLineupSubstitutes")
        var homeLineupSubstitutes: String?,

        @SerializedName("strAwayLineupGoalkeeper")
        var awayLineupGoalkeeper: String?,

        @SerializedName("strAwayLineupDefense")
        var awayLineupDefense: String?,

        @SerializedName("strAwayLineupMidfield")
        var awayLineupMidfield: String?,

        @SerializedName("strAwayLineupForward")
        var awayLineupForward: String?,

        @SerializedName("strAwayLineupSubstitutes")
        var awayLineupSubstitutes: String?,

        @SerializedName("idHomeTeam")
        var homeTeamId: String,

        @SerializedName("idAwayTeam")
        var awayTeamId: String
)