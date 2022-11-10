package activities

import adapters.MovieSeatAdapter
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.attt.moviebookingapp.R
import data.models.MovieBookingModel
import data.models.MovieBookingModelImpl
import data.vos.MovieSeatVO
import delegates.SeatActionDelegate
import kotlinx.android.synthetic.main.activity_movie_seat.*

class MovieSeatActivity : AppCompatActivity(), SeatActionDelegate {
    companion object {
        private const val EXTRA_CINEMA_ID = "EXTRA_CINEMA_ID"
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_TIMESLOT_ID = "EXTRA_TIMESLOT_ID"
        private const val EXTRA_MOVIE_DATE = "EXTRA_MOVIE_DATE"
        private const val EXTRA_POSTER = "EXTRA_POSTER"
        private const val EXTRA_MOVIE_NAME = "EXTRA_MOVIE_NAME"
        private const val EXTRA_CINEMA_NAME = "EXTRA_CINEMA_NAME"
        private const val EXTRA_USER_EMAIL = "EXTRA_USER_EMAIL"
        private const val EXTRA_USER_PASSWORD = "EXTRA_USER_PASSWORD"
        fun newIntent(
            context: Context,
            cinemaId: Int?,
            movieDate: String?,
            movieName: String?,
            cinemaName: String?,
            email: String?,
            password: String?,
            timeslotId:Int?,
            movieId:Int?,
            poster:String?
        ): Intent {
            val intent = Intent(context, MovieSeatActivity::class.java)
            intent.putExtra(EXTRA_CINEMA_ID, cinemaId)
            intent.putExtra(EXTRA_MOVIE_DATE, movieDate)
            intent.putExtra(EXTRA_CINEMA_NAME, cinemaName)
            intent.putExtra(EXTRA_USER_EMAIL, email)
            intent.putExtra(EXTRA_USER_PASSWORD, password)
            intent.putExtra(EXTRA_TIMESLOT_ID,timeslotId)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_MOVIE_NAME, movieName)
            intent.putExtra(EXTRA_POSTER,poster)
            return intent

        }
    }

    lateinit var mMovieSeatAdapter: MovieSeatAdapter

    private lateinit var mSeatList: List<MovieSeatVO>
    private var mSelectedSeat: List<String> = listOf()
    private var mTicketCount: Int = 0
    private var mPrice: Int = 0
    private  var mRow:List<String?> = listOf()


    // Toast.makeText(this, "$cinemaId,$date", Toast.LENGTH_LONG).show()


    //model
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_seat)

        setupListener()
        setupAdapters()
        requestData()

    }

    private fun requestData() {
        val cinemaId = intent?.getIntExtra(EXTRA_CINEMA_ID, 0)
        val date = intent?.getStringExtra(EXTRA_MOVIE_DATE)
        val cinemaName = intent.getStringExtra(EXTRA_CINEMA_NAME)
        val email = intent?.getStringExtra(EXTRA_USER_EMAIL)
        val password = intent.getStringExtra(EXTRA_USER_PASSWORD)
        val timeslotId=intent?.getIntExtra(EXTRA_TIMESLOT_ID,0)
        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        val poster=intent.getStringExtra(EXTRA_POSTER)
        val movieName = intent?.getStringExtra(EXTRA_MOVIE_NAME)

        mMovieBookingModel.getMovieSeats(
            timeslotId = timeslotId,
            bookingDate = date,
            onSuccess = {
                mSeatList = it
                mMovieSeatAdapter.setNewData(mSeatList)
                tvMovieName.text = movieName
                tvCinema.text = cinemaName
            },
            onFailure = {

            })
        btnBuyTicket.setOnClickListener {
            startActivity(
                SnackActivity.newIntent(
                    this,
                    cinemaId = cinemaId,
                    movieDate = date,
                    price = mPrice,
                    email = email,
                    password=password,
                    row = mRow.joinToString (",")?:"",
                    seatNumber = mSelectedSeat.joinToString (",") ?:"",
                    timeslotId=timeslotId,
                    movieId=movieId,
                    movieName = movieName,
                    poster=poster,
                    cinemaName=cinemaName

                )
            )

        }
    }

    private fun setupAdapters() {
        mMovieSeatAdapter = MovieSeatAdapter(this)
        rvMovieSeats.adapter = mMovieSeatAdapter
        rvMovieSeats.layoutManager = GridLayoutManager(applicationContext, 14)

        // mMovieSeatAdapter.setNewData(DUMMY_SEATS)

    }

    private fun setupListener() {

        btnBack.setOnClickListener {
            super.onBackPressed()
        }

    }

    override fun onTapSeat(seatName: String) {
        for (seat in mSeatList) {
            if (seat.seatName == seatName) {
                if (seat.isMovieSeatAvailable())
                if (!seat.isSelected) {
                    seat.isSelected = true
                    mRow += seat.symbol
                    mMovieSeatAdapter.setNewData(mSeatList)
                    mSelectedSeat += seat.seatName
                    mTicketCount += 1
                    mPrice += seat.price
                }
                else{
                    seat.isSelected=false
                    mRow -= seat.symbol
                    mMovieSeatAdapter.setNewData(mSeatList)
                    mSelectedSeat -= seat.seatName
                    mTicketCount -= 1
                    mPrice -= seat.price

                }
            }

        }
        tvRowNo.text = mSelectedSeat.joinToString (",")  ?:""
        tvTicketCount.text = mTicketCount.toString()
        btnBuyTicket.text = "Buy Ticket for $$mPrice"

    }


}