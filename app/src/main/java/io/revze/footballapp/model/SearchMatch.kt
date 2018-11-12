package io.revze.footballapp.model

import com.google.gson.annotations.SerializedName

data class SearchMatch(
        @SerializedName("idEvent")
        val eventId: String,

        @SerializedName("strHomeTeam")
        val homeTeam: String,

        @SerializedName("strAwayTeam")
        val awayTeam: String,

        @SerializedName("intHomeScore")
        val homeScore: String?,

        @SerializedName("intAwayScore")
        val awayScore: String?,

        @SerializedName("dateEvent")
        val dateEvent: String,

        @SerializedName("strTime")
        val timeEvent: String
)