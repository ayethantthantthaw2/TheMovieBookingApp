package com.attt.themovieapp.network.responses


import com.google.gson.annotations.SerializedName
import data.vos.ActorVO

data class GetCreditsByMovieResponse(
    @SerializedName("cast")
    val cast:List<ActorVO>,


)
