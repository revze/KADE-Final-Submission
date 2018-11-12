package io.revze.footballapp.model

import com.google.gson.annotations.SerializedName

data class PlayersResponse (
        @SerializedName("player")
        val players: List<Player>?
)