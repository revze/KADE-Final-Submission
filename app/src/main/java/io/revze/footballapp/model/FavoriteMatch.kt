package io.revze.footballapp.model

import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class FavoriteMatch(
        @Id
        var id: Long = 0,

        val eventId: String,

        val homeTeam: String,

        val awayTeam: String,

        val homeScore: String?,

        val awayScore: String?,

        val dateEvent: String,

        val timeEvent: String
)