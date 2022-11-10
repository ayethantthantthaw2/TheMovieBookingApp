package data.models

import persistence.MovieBookingDatabase
import android.content.Context
import android.util.Log
import data.vos.*
import network.dataAgents.MovieBookingDataAgent
import network.dataAgents.MovieBookingDataAgentImpl
import network.response.CheckOutRequest

object MovieBookingModelImpl : MovieBookingModel {
    private val mMovieBookingDataAgent: MovieBookingDataAgent = MovieBookingDataAgentImpl

    //database
    private var mMovieDatabase: MovieBookingDatabase? = null

    fun initDatabase(context: Context) {
        mMovieDatabase = MovieBookingDatabase.getDBInstance(context)
    }

    override fun registerWithEmail(
        name: String?,
        email: String?,
        phone: String?,
        password: String?,
        onSuccess: (UserVO?) -> Unit,
        onFailure: (String) -> Unit
    ) {


        mMovieBookingDataAgent.registerWithEmail(
            name = name,
            email = email,
            phone = phone,
            password = password,
            onSuccess = {
                //process in data layer
                val user = it.first
                user.token = "Bearer " + it.second

                // insert data to persistence layer
                mMovieDatabase?.userDao()?.insertUser(user)

                Log.d("TAG", "loginWithEmail:${it.first} ")

                onSuccess(it.first)


            },
            onFailure = onFailure
        )
    }

    override fun loginWithEmail(
        email: String?,
        password: String?,
        onSuccess: (UserVO?) -> Unit,
        onFailure: (String) -> Unit
    ) {


        mMovieBookingDataAgent.loginWithEmail(
            email = email,
            password = password,
            onSuccess = {

                //process in datalayer
                val user = it.first
                user.token = "Bearer " + it.second

                //insert data into persistence
                mMovieDatabase?.userDao()?.insertUser(user)


                Log.d("TAG", "loginWithEmail:${it.first} ")

                onSuccess(it.first)

            },
            onFailure = {
                Log.d("Error", "$it")
            }
        )
    }

    override fun getProfile(

        authorization: String?,
        onSuccess: (UserVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //get data from database
        mMovieDatabase?.userDao()?.getUser()?.first()?.let { onSuccess(it) }

        mMovieDatabase?.userDao()?.getUser()?.first().let { userObj ->
            mMovieBookingDataAgent.getProfile(

                authorization = userObj?.token,
                onSuccess = {user ->
                    user.token = userObj?.token
                    mMovieDatabase?.userDao()?.insertUser(user)
                    onSuccess(user)
                    // mMovieDatabase?.userDao()?.insertUser(it)
                    // onSuccess(it)
//                    if (it != null) {
//                        mMovieDatabase?.userDao()?.insertUser(it)
//                    }
//                    it?.token = userToken
//                    it?.let { it1 -> onSuccess(it1) }
                },
                onFailure = onFailure

            )
        }

    }

    override fun getProfileForWelcome(onSuccess: (UserVO) -> Unit, onFailure: (String) -> Unit) {


        mMovieDatabase?.userDao()?.getUser()?.first().let {
            mMovieBookingDataAgent.getProfile(
                authorization = it?.token,
                onSuccess = { user ->
                    user.token = it?.token
                    mMovieDatabase?.userDao()?.insertUser(user)
                    onSuccess(user)
                },
                onFailure = onFailure

            )


            it?.let { it1 -> onSuccess(it1) }
        }


    }


    override fun logoutUser(
        authorization: String?,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDatabase?.userDao()?.getUser()?.first().let { userObj ->
            mMovieBookingDataAgent.logoutUser(
                authorization = userObj?.token,
                onSuccess = {
                    //this.userToken = null
                    mMovieDatabase?.userDao()?.deleteUser()
                    onSuccess()

                },
                onFailure = {
                    Log.d("Tag", "$it")
                }


            )
        }

    }

    override fun getCinema(
        movieId: String?,
        date: String?,
        authorization: String?,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDatabase?.timeslotsDao()?.getCinema(date = date).let {
            if (it != null) {
                onSuccess(it.cinemas)
            }
        }

        mMovieDatabase?.userDao()?.getUser()?.first().let { userObj ->
            mMovieBookingDataAgent.getCinema(
                movieId = movieId,
                date = date,
                authorization = userObj?.token,
                onSuccess = { cinemaList ->
                    val entityToSave = DateCinemaAndTimeslots(
                        date = date ?: "",
                        cinemas = cinemaList
                    )
                    onSuccess(cinemaList)
                    mMovieDatabase?.timeslotsDao()?.insertCinema(entityToSave)


                },
                onFailure = {
                    Log.d("Tag", "$it")
                }
            )
        }


    }

    override fun getMovieSeats(
        timeslotId: Int?,
        bookingDate: String?,
        authorization: String?,
        onSuccess: (List<MovieSeatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDatabase?.userDao()?.getUser()?.first().let {
            if (it != null) {
                mMovieBookingDataAgent.getMovieSeats(
                    timeslotId = timeslotId,
                    bookingDate = bookingDate,
                    authorization = it.token,
                    onSuccess = onSuccess,
                    onFailure = onFailure
                )
            }
        }

    }

    override fun getSnack(
        authorization: String?,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDatabase?.snackDao()?.getSnack().let {
            if (it != null) {
                onSuccess(it)
            }
        }
        mMovieDatabase?.userDao()?.getUser()?.first().let { userObj ->

            mMovieBookingDataAgent.getSnack(
                authorization = userObj?.token,
                onSuccess = {
                    mMovieDatabase?.snackDao()?.insertSnacks(it)
                    onSuccess(it)
                },
                onFailure = onFailure
            )

        }

    }

    override fun getPaymentMethod(
        authorization: String?,
        onSuccess: (List<PaymentMethodVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDatabase?.paymentMethodDao()?.getPaymentMethod().let {
            if (it != null) {
                onSuccess(it)
            }
        }

        mMovieDatabase?.userDao()?.getUser()?.first().let { userObj ->

            mMovieBookingDataAgent.getPaymentMethod(
                authorization = userObj?.token,
                onSuccess = {
                    mMovieDatabase?.paymentMethodDao()?.insertPaymentMethod(it)
                    onSuccess(it)
                },
                onFailure = onFailure
            )

        }
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

        mMovieDatabase?.userDao()?.getUser()?.first().let { userObj ->
            mMovieBookingDataAgent.createNewCard(
                cardNumber = cardNumber,
                cardHolder = cardHolder,
                expirationDate = expirationDate,
                cvc = cvc,
                authorization = userObj?.token,
                onSuccess = onSuccess,
                onFailure = onFailure
            )
        }

    }


    override fun checkOut(
        timeslotId: Int?,
        row: String?,
        seatNumber: String?,
        bookingDate: String?,
        total: Int?,
        movieID: Int?,
        cardId: Int?,
        cinemaId: Int?,
        snacks: List<SelectedSnackVO>?,
        onSuccess: (CheckOutVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val mCheckOutRequest = CheckOutRequest(
            timeslotId,
            row,
            seatNumber,
            bookingDate,
            total,
            movieID,
            cardId,
            cinemaId,
            snacks
        )
        mMovieDatabase?.userDao()?.getUser()?.first().let { userObj ->
            mMovieBookingDataAgent.checkOut(
                checkOutRequest = mCheckOutRequest,
                authorization = userObj?.token,
                onSuccess = onSuccess,
                onFailure = onFailure

            )
        }

    }
}