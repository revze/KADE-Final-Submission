package io.revze.footballapp.model

import com.google.gson.annotations.SerializedName

data class TeamDetailResponse (
        @SerializedName("teams")
        val teams: List<Team>?
)