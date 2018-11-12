package io.revze.footballapp.model

import com.google.gson.annotations.SerializedName

data class SearchTeamResponse (
        @SerializedName("teams")
        val teams: List<Team>?
)