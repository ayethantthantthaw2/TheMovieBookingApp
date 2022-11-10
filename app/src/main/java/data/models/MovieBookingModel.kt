package data.models

import data.vos.*
import network.response.RegisterResponse

interface MovieBookingModel {
    fun registerWithEmail(
        name: String?,
        email: String?,
        phone: String?,
        password: String?,
        onSuccess: (UserVO?) -> Unit,
        onFailure: (String) -> Unit
    )

    fun loginWithEmail(
        email: String?,
        password: String?,
        onSuccess: (UserVO?) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getProfile(

        authorization: String? = null,
        onSuccess: (UserVO) -> Unit,
        onFailure: (String) -> Unit

    )
    fun getProfileForWelcome(

        onSuccess: (UserVO) -> Unit,
        onFailure: (String) -> Unit

    )

    fun logoutUser(
        authorization: String? = null,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCinema(
        movieId: String?,
        date: String?,
        authorization: String? = null,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit

    )

    fun getMovieSeats(
        timeslotId: Int?,
        bookingDate: String?,
        authorization: String? = null,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSnack(
        authorization: String? = null,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPaymentMethod(
        authorization: String? = null,
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun createNewCard(
        cardNumber: String?,
        cardHolder: String?,
        expirationDate: String?,
        cvc: String?,
        authorization: String? = null,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun checkOut(
        timeslotId: Int?,
        row: String?,
        seatNumber: String?,
        bookingDate: String?,
        total: Int??,
        movieID: Int?,
        cardId: Int?,
        cinemaId: Int?,
        snacks: List<SelectedSnackVO>?,
        onSuccess: (CheckOutVO) -> Unit,
        onFailure: (String) -> Unit

    )

}