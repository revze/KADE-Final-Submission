package io.revze.footballapp.model

import com.google.gson.annotations.SerializedName

data class League (@SerializedName("idLeague")
                   val id: String,

                   @SerializedName("strLeague")
                   val name: String)