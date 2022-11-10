package activities

//import adapters.CarouselViewAdapter
import adapters.CarouselViewAdapter
import alirezat775.lib.carouselview.Carousel
import alirezat775.lib.carouselview.CarouselListener
import alirezat775.lib.carouselview.CarouselView
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.attt.moviebookingapp.R
import com.google.gson.Gson
import data.models.MovieBookingModel
import data.models.MovieBookingModelImpl
import data.vos.CardVO
import data.vos.CheckOutVO
import data.vos.SelectedSnackVO
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.activity_snack.btnBack

class PaymentActivity : AppCompatActivity() {
    companion object {
        private const val EXTRA_SEAT_NUMBER = "EXTRA_SEAT_NUMBER"
        private const val EXTRA_TOTAL = "EXTRA_TOTAL"
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_MOVIE_DATE = "EXTRA_MOVIE_DATE"
        private const val EXTRA_USER_EMAIL = "EXTRA_USER_EMAIL"
        private const val EXTRA_USER_PASSWORD = "EXTRA_USER_PASSWORD"
        private const val EXTRA_CINEMA_ID = "EXTRA_CINEMA_ID"
        private const val EXTRA_ROW = "EXTRA_ROW"
        private const val EXTRA_CINEMA_NAME = "EXTRA_CINEMA_NAME"
        private const val EXTRA_TIMESLOT_ID = "EXTRA_TIMESLOT_ID"
        private const val EXTRA_SNACKS = "EXTRA_SNACKS"
        private const val EXTRA_POSTER = "EXTRA_POSTER"
        private const val EXTRA_MOVIE_NAME = "EXTRA_MOVIE_NAME"

        fun newIntent(
            context: Context,
            total: Int?,
            email: String?,
            password: String?,
            cinemaID: String?,
            row: String?,
            seatNumber: String?,
            date: String?,
            timeSlotId: Int?,
            movieId: Int?,
            snacks: String?,
            movieName: String?,
            poster: String?,
            cinemaName:String?
        ): Intent {
            val intent = Intent(context, PaymentActivity::class.java)
            intent.putExtra(EXTRA_TOTAL, total)
            intent.putExtra(EXTRA_USER_EMAIL, email)
            intent.putExtra(EXTRA_USER_PASSWORD, password)
            intent.putExtra(EXTRA_CINEMA_ID, cinemaID)
            intent.putExtra(EXTRA_SEAT_NUMBER, seatNumber)
            intent.putExtra(EXTRA_MOVIE_DATE, date)
            intent.putExtra(EXTRA_ROW, row)
            intent.putExtra(EXTRA_TIMESLOT_ID, timeSlotId)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_SNACKS, snacks)
            intent.putExtra(EXTRA_MOVIE_NAME, movieName)
            intent.putExtra(EXTRA_POSTER, poster)
            intent.putExtra(EXTRA_CINEMA_NAME,cinemaName)

            return intent
        }
    }
//    var email:String?=null
//    var password:String?=null
//    var seatNumber:String?=null
//    var date:String?=null
//    var cinemaId:Int?=null
//    var row :String?=null
//    var total:Int? = null
//    var timeslotId:Int? = null
//
//    var movieId :Int?=null
//    var snacks :String?=null
//    var poster:String? = null
//    var movieName :String? = null

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    private lateinit var mCarouselViewAdapter: CarouselViewAdapter

    private var mSelectCardID: Int? = 0
    private lateinit var mCardList: List<CardVO>

    private lateinit var mCheckout: CheckOutVO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        // getParam()
        setupAdapter()
        setupListener()


        var total = intent.getIntExtra(EXTRA_TOTAL, 0)
        tvTotalPrice.text = "$$total"
        requestData()

    }

//    private fun getParam() {
//        email = intent?.getStringExtra(EXTRA_USER_EMAIL)
//        password = intent.getStringExtra(EXTRA_USER_PASSWORD)
//        seatNumber = intent?.getStringExtra(EXTRA_SEAT_NUMBER)
//        date = intent?.getStringExtra(EXTRA_MOVIE_DATE)
//        cinemaId = intent?.getIntExtra(EXTRA_CINEMA_ID, 0)
//        row = intent?.getStringExtra(EXTRA_ROW)
//        total = intent?.getIntExtra(EXTRA_TOTAL, 0)
//        intent?.getIntExtra(EXTRA_TIMESLOT_ID, 0)
//        movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
//        snacks = intent?.getStringExtra(EXTRA_SNACKS)
//        poster = intent.getStringExtra(EXTRA_POSTER)
//        movieName = intent?.getStringExtra(EXTRA_MOVIE_NAME)
//    }

    private fun requestData() {

        val email = intent?.getStringExtra(EXTRA_USER_EMAIL)
        val password = intent.getStringExtra(EXTRA_USER_PASSWORD)
        val cinemaId = intent?.getIntExtra(EXTRA_CINEMA_ID, 0)
        val date = intent?.getStringExtra(EXTRA_MOVIE_DATE)
        val row = intent?.getStringExtra(EXTRA_ROW)
        val seatNumber = intent?.getStringExtra(EXTRA_SEAT_NUMBER)
        val timeslotId = intent?.getIntExtra(EXTRA_TIMESLOT_ID, 0)
        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        val poster = intent.getStringExtra(EXTRA_POSTER)
        val total = intent.getIntExtra(EXTRA_TOTAL, 0)
        val movieName = intent?.getStringExtra(EXTRA_MOVIE_NAME)
        val snacks = intent.getStringExtra(EXTRA_SNACKS)
        val cinemaName=intent.getStringExtra(EXTRA_CINEMA_NAME)
        //convert string to object
        val snackList = Gson().fromJson(snacks, mutableListOf<SelectedSnackVO>().javaClass)


        mMovieBookingModel.getProfile(
            onSuccess = {
                mCardList = it.cards
                mSelectCardID = it.id
                mCarouselViewAdapter.setNewData(it.cards.reversed())

            }, onFailure = {

            })
        btnConfirm.setOnClickListener {

            mMovieBookingModel.checkOut(
                timeslotId = timeslotId,
                row = row,
                seatNumber = seatNumber,
                bookingDate = date,
                total = total,
                movieID = movieId,
                cardId = mSelectCardID,
                snacks = snackList,
                cinemaId = cinemaId,
                onSuccess = {
                    mCheckout = it
                    startActivity(
                        VoucherActivity.newIntent(
                            this,
                            Gson().toJson(mCheckout),
                            movieName = movieName,
                            poster = poster,
                            cinemaName=cinemaName
                        )
                    )
                },
                onFailure = {

                }


            )
//            val intent = Intent(this, VoucherActivity::class.java)
//            startActivity(intent)


        }

    }


    private fun setupAdapter() {
        mCarouselViewAdapter = CarouselViewAdapter()
        val carousel = Carousel(this, rvCarouselView, mCarouselViewAdapter)

        carousel.setOrientation(CarouselView.HORIZONTAL, false)
        carousel.scaleView(true)
        carousel.addCarouselListener(object : CarouselListener {
            override fun onPositionChange(position: Int) {

                mSelectCardID = mCardList.reversed()[position].id
                mCardList.reversed()[position].isSelected = true
                mCarouselViewAdapter.setNewData(mCardList.reversed())

            }

            override fun onScroll(dx: Int, dy: Int) {

            }

        })
    }

    private fun setupListener() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }
        rlAddNewCard.setOnClickListener {
            startActivityForResult(AddNewCardActivity.newIntent(this), 100)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {

            requestData()


        }

    }
}