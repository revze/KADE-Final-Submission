package io.revze.footballapp.model

import com.google.gson.annotations.SerializedName

data class Team (
        @SerializedName("idTeam")
        val id: String,

        @SerializedName("strTeam")
        val name: String,

        @SerializedName("strTeamBadge")
        val logo: String,

        @SerializedName("intFormedYear")
        val formedYear: String,

        @SerializedName("strStadium")
        val stadium: String,

        @SerializedName("strDescriptionEN")
        val description: String
)