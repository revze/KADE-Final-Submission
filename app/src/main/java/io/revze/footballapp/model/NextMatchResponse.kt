package io.revze.footballapp.model

import com.google.gson.annotations.SerializedName

data class NextMatchResponse (
        @SerializedName("events")
        val nextMatch: List<NextMatch>?
)