package network.response

import com.google.gson.annotations.SerializedName
import data.vos.MovieSeatVO

data class MovieSeatsResponse(
    @SerializedName("data")
    val data:List<List<MovieSeatVO>>
)

