package data.vos

import com.google.gson.annotations.SerializedName

data class CheckOutVO(
    //"id": 1541,
    //        "booking_no": "Cinema I-3092-7065",
    //        "booking_date": "2022-09-03",
    //        "row": "H",
    //        "seat": "H-7",
    //        "total_seat": 1,
    //        "total": "$18",
    //        "movie_id": 610150,
    //        "cinema_id": 1,
    //        "username": "QQ",
    //        "timeslot": {
    //            "cinema_day_timeslot_id": 12,
    //            "start_time": "12:00 PM"
    //        },
    //        "snacks": [

    //        "qr_code": "uploads/img/qr-code/img-Cinema I-3092-7065.png"
    //    }
    //}
    @SerializedName("id")
    val id: Int?,

    @SerializedName("booking_no")
    val bookingNo: String?,

    @SerializedName("booking_date")
    val bookingDate: String?,

    @SerializedName("row")
    val row: String?,

    @SerializedName("seat")
    val seat: String?,

    @SerializedName("total_seat")
    val totalSeat: Int?,

    @SerializedName("total")
    val total: String?,

    @SerializedName("movie_id")
    val movieId: Int?,

    @SerializedName("cinema_id")
    val cinemaId: Int?,

    @SerializedName("username")
    val userName: String?,

    @SerializedName("timeslot")
    val timeSlot: TimeslotsVO?,

    @SerializedName("snacks")
    val snacks: List<SnackVO>?,

    @SerializedName("qr_code")
    val qrCode: String?

)
