package activities

import adapters.CardAdapter
import adapters.ComboAdapter
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.attt.moviebookingapp.R
import com.google.gson.Gson
import data.models.MovieBookingModel
import data.models.MovieBookingModelImpl
import data.vos.PaymentMethodVO
import data.vos.SelectedSnackVO
import data.vos.SnackVO
import delegates.CountActionDelegate
import delegates.PaymentMethodActionDelegate
import kotlinx.android.synthetic.main.activity_snack.*
import kotlinx.android.synthetic.main.activity_snack.btnBack

class SnackActivity : AppCompatActivity(), PaymentMethodActionDelegate, CountActionDelegate {
    companion object {
        private const val EXTRA_TIMESLOT_ID = "EXTRA_TIMESLOT_ID"
        private const val EXTRA_CINEMA_ID = "EXTRA_CINEMA_ID"
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_SEAT_NUMBER = "EXTRA_SEAT_NUMBER"
        private const val EXTRA_MOVIE_DATE = "EXTRA_MOVIE_DATE"
        private const val EXTRA_CINEMA_NAME = "EXTRA_CINEMA_NAME"
        private const val EXTRA_PRICE = "EXTRA_PRICE"
        private const val EXTRA_USER_EMAIL = "EXTRA_USER_EMAIL"
        private const val EXTRA_USER_PASSWORD = "EXTRA_USER_PASSWORD"
        private const val EXTRA_ROW = "EXTRA_ROW"
        private const val EXTRA_POSTER = "EXTRA_POSTER"
        private const val EXTRA_MOVIE_NAME = "EXTRA_MOVIE_NAME"
        fun newIntent(
            context: Context,
            cinemaId: Int?,
            movieDate: String?,
            price: Int?,
            email: String?,
            password: String?,
            row: String?,
            seatNumber: String?,
            timeslotId: Int?,
            movieId: Int?,
            movieName: String?,
            poster: String?,
            cinemaName:String?
        ): Intent {
            val intent = Intent(context, SnackActivity::class.java)
            intent.putExtra(EXTRA_CINEMA_ID, cinemaId)
            intent.putExtra(EXTRA_MOVIE_DATE, movieDate)
            intent.putExtra(EXTRA_PRICE, price)
            intent.putExtra(EXTRA_USER_EMAIL, email)
            intent.putExtra(EXTRA_USER_PASSWORD, password)
            intent.putExtra(EXTRA_SEAT_NUMBER, seatNumber)
            intent.putExtra(EXTRA_ROW, row)
            intent.putExtra(EXTRA_TIMESLOT_ID, timeslotId)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_MOVIE_NAME, movieName)
            intent.putExtra(EXTRA_POSTER, poster)
            intent.putExtra(EXTRA_CINEMA_NAME,cinemaName)
            return intent

        }
    }

    private lateinit var mComboAdapter: ComboAdapter
    private lateinit var mCardAdapter: CardAdapter
    private var mSubTotal: Int = 0
    private var mTotal: Int = 0


    private lateinit var mSnackList: List<SnackVO>
    private var mSelectedSnack: List<SelectedSnackVO> = listOf()

    private lateinit var mPaymentMethodList: List<PaymentMethodVO>

    //model
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snack)

        setupListener()
        setupAdapter()
        requestData()
        getSubTotal(0)

        btnPay.setOnClickListener {
            getSelectedSnacks(mSnackList)

            val email = intent?.getStringExtra(EXTRA_USER_EMAIL)
            val password = intent.getStringExtra(EXTRA_USER_PASSWORD)
            val cinemaId = intent?.getIntExtra(EXTRA_CINEMA_ID, 0).toString()
            val date = intent?.getStringExtra(EXTRA_MOVIE_DATE)
            val row = intent?.getStringExtra(EXTRA_ROW)
            val seatNumber = intent?.getStringExtra(EXTRA_SEAT_NUMBER)
            val timeslotId = intent?.getIntExtra(EXTRA_TIMESLOT_ID, 0)
            val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
            val poster = intent.getStringExtra(EXTRA_POSTER)
            val movieName = intent?.getStringExtra(EXTRA_MOVIE_NAME)
            val cinemaName=intent?.getStringExtra(EXTRA_CINEMA_NAME)
            startActivity(
                PaymentActivity.newIntent(
                    this,
                    total = mTotal,
                    email = email,
                    password = password,
                    cinemaID = cinemaId,
                    row = row,
                    seatNumber = seatNumber,
                    date = date,
                    timeSlotId = timeslotId,
                    movieId = movieId,
                    snacks = Gson().toJson(mSelectedSnack),
                    movieName = movieName,
                    poster = poster,
                    cinemaName=cinemaName
                )
            )

            finish()


        }


    }

    private fun getSubTotal(subTotal: Int) {
        var price = intent.getIntExtra(EXTRA_PRICE, 0)
        price += subTotal
        btnPay.text = "Pay $${price.toString()}"
        mTotal = price

    }

    private fun getSelectedSnacks(snacks: List<SnackVO>) {
        for (s in snacks) {
            if (s.qty >= 1) {
                mSelectedSnack += SelectedSnackVO(s.id, s.qty)

            } else {
                mSelectedSnack -= SelectedSnackVO(s.id, s.qty)
            }
        }


    }


    private fun requestData() {
        mMovieBookingModel.getSnack(
            onSuccess = {
                mSnackList = it
                mComboAdapter.setNewData(mSnackList)

            },
            onFailure = {

            }
        )
        mMovieBookingModel.getPaymentMethod(
            onSuccess = {
                mPaymentMethodList = it
                mCardAdapter.setNewData(mPaymentMethodList)
            },
            onFailure = {

            }
        )
    }

    private fun setupAdapter() {
        mComboAdapter = ComboAdapter(this)
        rvCombo.adapter = mComboAdapter
        rvCombo.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        mCardAdapter = CardAdapter(this)
        rvCard.adapter = mCardAdapter
        rvCard.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
    }

    private fun setupListener() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }


    }

    override fun onTapPaymentMethod(id: Int) {
        for (paymentMethod in mPaymentMethodList) {
            if (paymentMethod.id == id)
                paymentMethod.isSelected = true
            mCardAdapter.setNewData(mPaymentMethodList)
        }
    }

    override fun onTapPlus(snackId: Int) {
        //var price = intent.getIntExtra(EXTRA_PRICE, 0)
        for (snack in mSnackList) {
            if (snack.id == snackId) {
                snack.qty += 1
                mSubTotal += snack.price
                mComboAdapter.setNewData(mSnackList)
                getSubTotal(mSubTotal)


            }
        }

        tvSubtotal.text = "$mSubTotal$"


    }

    override fun onTapMinus(snackId: Int) {
        for (snack in mSnackList) {
            if (snack.id == snackId) {
                if (snack.qty >= 1) {
                    snack.qty -= 1
                    mSubTotal -= snack.price
                    mComboAdapter.setNewData(mSnackList)
                    getSubTotal(mSubTotal)
                }
            }

            tvSubtotal.text = "$mSubTotal$"
        }


    }


}