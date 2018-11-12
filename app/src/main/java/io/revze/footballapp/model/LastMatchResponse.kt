package io.revze.footballapp.model

import com.google.gson.annotations.SerializedName

data class LastMatchResponse (
        @SerializedName("events")
        val lastMatch: List<LastMatch>?
)