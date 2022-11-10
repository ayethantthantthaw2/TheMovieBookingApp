package network.response

import com.google.gson.annotations.SerializedName
import data.vos.SelectedSnackVO

class CheckOutRequest(

    @SerializedName("cinema_day_timeslot_id")
    val timeslotId: Int?,

    @SerializedName("row")
    val row: String?,

    @SerializedName("seat_number")
    val seatNumber: String?,

    @SerializedName("booking_date")
    val bookingDate: String?,

    @SerializedName("total_price")
    val total: Int?,

    @SerializedName("movie_id")
    val movieID: Int?,

    @SerializedName("card_id")
    val cardId: Int?,

    @SerializedName("cinema_id")
    val cinemaId: Int?,

    @SerializedName("snacks")
    val snacks: List<SelectedSnackVO>?
)
