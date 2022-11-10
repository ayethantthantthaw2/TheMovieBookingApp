package network

import network.response.*
import retrofit2.Call
import retrofit2.http.*
import utils.*

interface TheMovieBookingAPI {
    @POST(API_REGISTER_WITH_EMAIL)
    @FormUrlEncoded
    fun registerUser(
        @Field(PARAM_NAME) name: String?,
        @Field(PARAM_EMAIL) email: String?,
        @Field(PARAM_PHONE) phone: String?,
        @Field(PARAM_PASSWORD) password: String?,
    ): Call<RegisterResponse>

    @POST(API_LOGIN_WITH_EMAIL)
    @FormUrlEncoded
    fun loginUser(
        @Field(PARAM_EMAIL) email: String?,
        @Field(PARAM_PASSWORD) password: String?,
    ): Call<RegisterResponse>

    @GET(API_PROFILE)
    fun getProfile(
        @Header(PARAM_AUTHORIZATION) authorization: String?
    ): Call<RegisterResponse>

    @POST(API_LOGOUT)
    fun logoutUser(
        @Header(PARAM_AUTHORIZATION) authorization: String?
    ): Call<LogoutResponse>

    @GET(API_CINEMA)
    fun getCinema(
        @Query(PARAM_MOVIE_ID) movieId: String?,
        @Query(PARAM_DATE) date: String?,
        @Header(PARAM_AUTHORIZATION) authorization: String?
    ): Call<CinemaResponse>

    @GET(API_MOVIE_SEATS)
    fun getMovieSeats(
        @Query(PARAM_TIMESLOT_ID) timeslotId: Int?,
        @Query(PARAM_BOOKING_DATE) bookingDate: String?,
        @Header(PARAM_AUTHORIZATION) authorization: String?
    ): Call<MovieSeatsResponse>

    @GET(API_SNACK)
    fun getSnack(
        @Header(PARAM_AUTHORIZATION) authorization: String?
    ): Call<SnackResponse>

    @GET(API_PAYMENT_METHOD)
    fun getPaymentMethod(
        @Header(PARAM_AUTHORIZATION) authorization: String?
    ): Call<PaymentMethodResponse>

    @POST(API_CARD)
    @FormUrlEncoded
    fun createNewCard(
        @Field(PARAM_CARD_NUMBER) cardNumber: String?,
        @Field(PARAM_CARD_HOLDER) cardHolder: String?,
        @Field(PARAM_EXPIRATION_DATE) expirationDate: String?,
        @Field(PARAM_CVC) cvc: String?,
        @Header(PARAM_AUTHORIZATION) authorization: String?
    ): Call<CardResponse>

    @POST(API_CHECKOUT)
    fun checkOut(
        @Header(PARAM_AUTHORIZATION) authorization: String?,
        @Body checkOutRequest: CheckOutRequest
    ): Call<CheckoutResponse>

}