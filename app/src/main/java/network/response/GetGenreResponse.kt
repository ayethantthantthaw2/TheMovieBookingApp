package com.attt.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import data.vos.GenreVO

data class GetGenreResponse(
    @SerializedName("genres")
    val genres:List<GenreVO>?
)