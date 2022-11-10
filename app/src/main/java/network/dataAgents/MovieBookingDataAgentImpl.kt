package network.dataAgents

import data.vos.*
import network.TheMovieBookingAPI
import network.response.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utils.BASE_URL
import java.util.concurrent.TimeUnit
import kotlin.math.log

object MovieBookingDataAgentImpl : MovieBookingDataAgent {
    private var mTheMovieBookingApi: TheMovieBookingAPI? = null

    init {
        val interceptor = HttpLoggingInterceptor()
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor = interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mTheMovieBookingApi = retrofit.create(TheMovieBookingAPI::class.java)
    }

    override fun registerWithEmail(
        name: String?,
        email: String?,
        phone: String?,
        password: String?,
        onSuccess: (Pair<UserVO, String>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        mTheMovieBookingApi?.registerUser(
            name = name,
            email = email,
            phone = phone,
            password = password
        )
            ?.enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(Pair(it.data, it.token))

                        }

                    } else {
                        onFailure(response.message())
                    }

                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {

                }

            })
    }

    override fun loginWithEmail(
        email: String?,
        password: String?,
        onSuccess: (Pair<UserVO, String>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.loginUser(email = email, password = password)
            ?.enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(Pair(it.data, it.token))

                        }

                    } else {
                        onFailure(response.message())
                    }

                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {

                }

            })
    }

    override fun getProfile(

        authorization: String?,
        onSuccess: (UserVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getProfile(

            authorization = authorization
        )
            ?.enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(it.data)

                        }

                    } else {
                        onFailure(response.message())
                    }

                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {

                }

            })
    }

    override fun logoutUser(
        authorization: String?,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.logoutUser(authorization = authorization)
            ?.enqueue(object : Callback<LogoutResponse> {
                override fun onResponse(
                    call: Call<LogoutResponse>,
                    response: Response<LogoutResponse>
                ) {
                    if (response.isSuccessful) {
                        onSuccess()
                    }


                }

                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {

                }

            })

    }

    override fun getCinema(
        movieId: String?,
        date: String?,
        authorization: String?,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getCinema(
            movieId = movieId,
            date = date,
            authorization = authorization
        )
            ?.enqueue(object : Callback<CinemaResponse> {
                override fun onResponse(
                    call: Call<CinemaResponse>,
                    response: Response<CinemaResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(it.data)
                        }
                    } else{
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<CinemaResponse>, t: Throwable) {
                    onFailure(t.localizedMessage)
                }

            })

    }

    override fun getMovieSeats(
        timeslotId: Int?,
        bookingDate: String?,
        authorization: String?,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getMovieSeats(
            timeslotId = timeslotId,
            bookingDate = bookingDate,
            authorization = authorization
        )?.enqueue(object : Callback<MovieSeatsResponse> {
            override fun onResponse(
                call: Call<MovieSeatsResponse>,
                response: Response<MovieSeatsResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val seats = it.data.flatten()
                        onSuccess(seats)
                    }
                } else {
                    val message = response.message()
                }
            }

            override fun onFailure(call: Call<MovieSeatsResponse>, t: Throwable) {
                val failure = t.localizedMessage
            }

        })
    }

    override fun getSnack(
        authorization: String?,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getSnack(authorization = authorization)
            ?.enqueue(object : Callback<SnackResponse> {
                override fun onResponse(
                    call: Call<SnackResponse>,
                    response: Response<SnackResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(it.data)
                        }
                    }
                }

                override fun onFailure(call: Call<SnackResponse>, t: Throwable) {

                }

            })
    }

    override fun getPaymentMethod(
        authorization: String?,
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.getPaymentMethod(authorization = authorization)
            ?.enqueue(object : Callback<PaymentMethodResponse> {
                override fun onResponse(
                    call: Call<PaymentMethodResponse>,
                    response: Response<PaymentMethodResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(it.data)
                        }
                    }
                }

                override fun onFailure(call: Call<PaymentMethodResponse>, t: Throwable) {

                }

            })
    }

    override fun createNewCard(
        cardNumber: String?,
        cardHolder: String?,
        expirationDate: String?,
        cvc: String?,
        authorization: String?,
        onSuccess: (List<CardVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.createNewCard(
            cardNumber = cardNumber,
            cardHolder = cardHolder,
            expirationDate = expirationDate,
            cvc = cvc,
            authorization = authorization
        )
            ?.enqueue(object : Callback<CardResponse> {
                override fun onResponse(
                    call: Call<CardResponse>,
                    response: Response<CardResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(it.data ?: listOf())
                        }
                    }
                }

                override fun onFailure(call: Call<CardResponse>, t: Throwable) {

                }

            })
    }

    override fun checkOut(
        checkOutRequest: CheckOutRequest,
        authorization: String?,
        onSuccess: (CheckOutVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieBookingApi?.checkOut(
            checkOutRequest = checkOutRequest,
            authorization = authorization
        )
            ?.enqueue(object : Callback<CheckoutResponse> {
                override fun onResponse(
                    call: Call<CheckoutResponse>,
                    response: Response<CheckoutResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(it.data)

                        }
                    }
                }

                override fun onFailure(call: Call<CheckoutResponse>, t: Throwable) {

                }

            })

    }
}