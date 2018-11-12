package io.revze.footballapp.model

import com.google.gson.annotations.SerializedName

data class SearchMatchResponse (
        @SerializedName("event")
        val match: List<SearchMatch>?
)