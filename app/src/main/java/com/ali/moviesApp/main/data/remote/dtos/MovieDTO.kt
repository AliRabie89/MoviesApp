

package com.ali.moviesApp.main.data.remote.dtos

import com.google.gson.annotations.SerializedName

data class MovieDTO (
    @SerializedName("id"      ) var id      : String? = null,
    @SerializedName("title"       ) var title       : String? = null,
    @SerializedName("poster" ) var poster : String? = null,
    @SerializedName("year") var releaseDate         : String? = null,
    @SerializedName("plot") var plot         : String? = null
)