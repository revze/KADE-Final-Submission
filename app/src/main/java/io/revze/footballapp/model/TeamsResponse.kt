package io.revze.footballapp.model

import com.google.gson.annotations.SerializedName

data class TeamsResponse (
        @SerializedName("teams")
        val teams: List<Team>?
)