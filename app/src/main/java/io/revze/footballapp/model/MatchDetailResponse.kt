package io.revze.footballapp.model

import com.google.gson.annotations.SerializedName

data class MatchDetailResponse (
        @SerializedName("events")
        val matchDetail: List<MatchDetail>
)