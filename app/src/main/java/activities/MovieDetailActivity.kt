package activities

import adapters.CastAdapter
import adapters.MovieGenreAdapter
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.attt.moviebookingapp.R
import com.attt.themovieapp.data.models.MovieModel
import com.attt.themovieapp.data.models.MovieModelImpl
import com.bumptech.glide.Glide
import data.vos.MovieVO
import kotlinx.android.synthetic.main.activity_movie_detail.*
import utils.IMAGE_BASE_URL

class MovieDetailActivity : AppCompatActivity() {
    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        private const val EXTRA_USER_EMAIL = "EXTRA_USER_EMAIL"
        private const val EXTRA_USER_PASSWORD = "EXTRA_USER_PASSWORD"
        fun newIntent(context: Context, movieId: Int?, email: String?, password: String?): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movieId)
            intent.putExtra(EXTRA_USER_EMAIL, email)
            intent.putExtra(EXTRA_USER_PASSWORD, password)
            return intent

        }
    }

    lateinit var mCastAdapter: CastAdapter
    lateinit var mMovieGenreAdapter: MovieGenreAdapter

    lateinit var mMovieName: String
    lateinit var mPosterPath:String

    //models
    private val mMovieModel: MovieModel = MovieModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setupMovieGenreAdapter()
        setupCastAdapter()
        setupListener()

        val email = intent?.getStringExtra(EXTRA_USER_EMAIL)
        val password = intent.getStringExtra(EXTRA_USER_PASSWORD)
        val movieId = intent?.getIntExtra(EXTRA_MOVIE_ID, 0)
        movieId?.let {
            requestData(movieId)
        }
        btnGetTicket.setOnClickListener {
            startActivity(
                MovieTimeActivity.newIntent(
                    this,
                    movieId = movieId,
                    movieName = mMovieName,
                    email=email,
                    password=password,
                    poster = mPosterPath
                )
            )
        }

    }

    private fun requestData(movieId: Int) {
        //Movie Detail
        mMovieModel.getMovieDetails(
            movieId = movieId.toString(),
            onSuccess = {
                bindData(it)
                it.genres?.let { it1 -> mMovieGenreAdapter.setNewData(it1) }
                mMovieName = it.title?:""
                mPosterPath=it.posterPath?:""
            },
            onFailure = {
                //show error message
            }

        )
        //Actors
        mMovieModel.getCreditsByMovie(
            movieId = movieId.toString(),
            onSuccess = {
                mCastAdapter.setNewData(it)

            }, onFailure = {
                //show error message
            }

        )

    }

    private fun bindData(movie: MovieVO) {
        Glide.with(this)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(ivPoster)
        tvMovieName.text = movie.title.toString() ?: ""
        rbMovieRating.rating = movie.getMovieBasedOnFiveStars()
        tvSummary.text = movie.overview.toString() ?: ""

    }


    private fun setupListener() {
        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setupMovieGenreAdapter() {
        mMovieGenreAdapter = MovieGenreAdapter()
        rvMovieGenre.adapter = mMovieGenreAdapter
        rvMovieGenre.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }


    private fun setupCastAdapter() {
        mCastAdapter = CastAdapter()
        rvCast.adapter = mCastAdapter
        rvCast.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
    }
}