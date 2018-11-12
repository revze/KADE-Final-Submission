package io.revze.footballapp.model

import com.google.gson.annotations.SerializedName

data class Player (@SerializedName("idPlayer")
                   val id: String,

                   @SerializedName("strPlayer")
                   val name: String,

                   @SerializedName("strCutout")
                   val photo: String,

                   @SerializedName("strFanart1")
                   val fanArt: String,

                   @SerializedName("strHeight")
                   val height: String,

                   @SerializedName("strWeight")
                   val weight: String,

                   @SerializedName("strPosition")
                   val position: String,

                   @SerializedName("strDescriptionEN")
                   val description: String
)