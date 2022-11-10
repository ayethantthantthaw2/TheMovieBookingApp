package network.response

import com.google.gson.annotations.SerializedName
import data.vos.CinemaVO
import data.vos.TimeslotsVO

data class CinemaResponse(
    @SerializedName("data")
    val data: List<CinemaVO>,
    )
