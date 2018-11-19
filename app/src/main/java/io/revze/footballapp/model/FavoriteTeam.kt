package io.revze.footballapp.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class FavoriteTeam(
        @Id
        var objectBoxId: Long = 0,

        val id: String,

        val name: String,

        val logo: String,

        val formedYear: String,

        val stadium: String,

        val description: String
)