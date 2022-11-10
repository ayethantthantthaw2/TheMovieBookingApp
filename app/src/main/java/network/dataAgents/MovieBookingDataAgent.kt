package network.dataAgents

import data.vos.*
import network.response.CheckOutRequest
import network.response.RegisterResponse

interface MovieBookingDataAgent {
    fun registerWithEmail(
        name: String?,
        email: String?,
        phone: String?,
        password: String?,
        onSuccess: (Pair<UserVO, String>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun loginWithEmail(
        email: String?,
        password: String?,
        onSuccess: (Pair<UserVO, String>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getProfile(
        authorization: String?,
        onSuccess: (UserVO) -> Unit,
        onFailure: (String) -> Unit

    )

    fun logoutUser(
        authorization: String?,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCinema(
        movieId: String?,
        date: String?,
        authorization: String?,
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
        checkOutRequest: CheckOutRequest,
        authorization: String? = null,
        onSuccess: (CheckOutVO) -> Unit,
        onFailure: (String) -> Unit

    )

}