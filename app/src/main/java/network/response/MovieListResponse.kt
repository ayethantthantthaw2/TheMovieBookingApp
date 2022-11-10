package network.response


import com.google.gson.annotations.SerializedName
import data.vos.DateVO
import data.vos.MovieVO

data class MovieListResponse(
    @SerializedName("page")
    val page:Int?,

    @SerializedName("date")
    val date:DateVO?,

    @SerializedName("results")
    val results:List<MovieVO>?
)