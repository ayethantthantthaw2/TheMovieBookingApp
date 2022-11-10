package activities

import adapters.CinemaAdapter
import adapters.DateAdapter
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.attt.moviebookingapp.R
import data.models.MovieBookingModel
import data.models.MovieBookingModelImpl
import data.vos.CinemaVO
import data.vos.DateVO
import delegates.DateActionDelegate
import delegates.TimeslotsActionDelegate
import kotlinx.android.synthetic.main.activity_movie_time.*
import kotlinx.android.synthetic.main.activity_movie_time.btnBack
import java.text.SimpleDateFormat
import java.util.*

class MovieTimeActivity : AppCompatActivity(), DateActionDelegate, TimeslotsActionDelegate {

    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_POSTER = "EXTRA_POSTER"
        private const val EXTRA_MOVIE_NAME = "EXTRA_MOVIE_NAME"
        private const val EXTRA_USER_EMAIL = "EXTRA_USER_EMAIL"
        private const val EXTRA_USER_PASSWORD = "EXTRA_USER_PASSWORD"
        fun newIntent(
            context: Context,
            movieId: Int?,
            movieName: String?,
            email: String?,
            password: String?,
            poster:String?
        ): Intent {
            val intent = Intent(context, MovieTimeActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_MOVIE_NAME, movieName)
            intent.putExtra(EXTRA_USER_EMAIL, email)
            intent.putExtra(EXTRA_USER_PASSWORD, password)
            intent.putExtra(EXTRA_POSTER,poster)
            return intent

        }
    }

    private lateinit var mDateAdapter: DateAdapter
    lateinit var mCinemaAdapter: CinemaAdapter

    lateinit var mDelegate: DateActionDelegate

    private var mTimeslotId: Int? = null
    private var mMovieId: String? = null
    private var mSelectedDate: String? = null
    lateinit var mCinemaName: String

    private var mCinemaId: Int? = null

    lateinit var mCinemaList: List<CinemaVO>


    private var movieDates = mutableListOf<DateVO>()


    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_time)

        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        val email = intent?.getStringExtra(EXTRA_USER_EMAIL)
        val password = intent.getStringExtra(EXTRA_USER_PASSWORD)
        val poster=intent.getStringExtra(EXTRA_POSTER)
        val movieName = intent?.getStringExtra(EXTRA_MOVIE_NAME)

        mMovieId = movieId.toString()
        // mTimeslotId=mCinemaList[0].timeslots[0].cinemaDayTimeslotId

        setupAdapters(this)
        generateDaysForTwoWeek()
        setupListener()


        btnNext.setOnClickListener {

            mTimeslotId?.let {
                startActivity(
                    MovieSeatActivity.newIntent(
                        this,
                        movieDate = mSelectedDate,
                        cinemaId = mCinemaId,
                        movieName = movieName,
                        cinemaName = mCinemaName,
                        email = email,
                        password = password,
                        timeslotId = mTimeslotId,
                        movieId = movieId,
                        poster = poster
                    )
                )

            }


        }


    }

    private fun requestData(date: String?) {
        mSelectedDate = date
        //get Cinemas
        mMovieBookingModel.getCinema(movieId = mMovieId, date = date,
            onSuccess = {
                mCinemaList = it
                mCinemaAdapter.setNewData(it)
                setupListener()
            },
            onFailure = {

            })
    }


    private fun setupListener() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }

    }


    private fun setupAdapters(delegate: DateActionDelegate) {
        this.mDelegate = delegate
        mDateAdapter = DateAdapter(mDelegate)
        rvDate.adapter = mDateAdapter
        rvDate.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)


        mCinemaAdapter = CinemaAdapter(this)
        rvCinema.adapter = mCinemaAdapter
        rvCinema.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

    }

    private fun generateDaysForTwoWeek() {

        for (i in 0..13) {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, i)
            val dates = calendar.time

            val dayOfWeek = SimpleDateFormat("EE", Locale.ENGLISH).format(dates)
            val day = SimpleDateFormat("dd", Locale.ENGLISH).format(dates)
            val date = SimpleDateFormat("yyyy-MM-dd").format(dates)

            movieDates.add(
                i, DateVO(
                    dayOfWeek = dayOfWeek, day = day, date = date, isSelected = false
                )
            )


        }
        mDateAdapter.setNewData(movieDates)
        movieDates[0].isSelected = true
        requestData(movieDates[0].date)

    }


    override fun onTapDate(selectedDate: String?) {
        mSelectedDate = selectedDate
        //select date
        for (date in movieDates) {
            if (date.date == selectedDate) {
                date.isSelected = true
                mDateAdapter.setNewData(movieDates)
                requestData(mSelectedDate)
                mTimeslotId = null

            }

        }

    }

    override fun onTapTimeSlot(timeslotId: Int) {
        mTimeslotId = timeslotId
        for (cinema in mCinemaList) {
            for (timeslots in cinema.timeslots)
                if (timeslots.cinemaDayTimeslotId == timeslotId) {
                    if (timeslots.isSelectedTimeslots == false) {
                        timeslots.isSelectedTimeslots = true
                        mCinemaName = cinema.cinema
                        mCinemaId=cinema.cinemaId
                        mCinemaAdapter.setNewData(mCinemaList)



                    } else {
                        timeslots.isSelectedTimeslots = false
                        mCinemaName = cinema.cinema
                        mCinemaAdapter.setNewData(mCinemaList)
                    }

                }
        }
    }
}