package activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.attt.moviebookingapp.R
import com.attt.themovieapp.data.models.MovieModel
import com.attt.themovieapp.data.models.MovieModelImpl
import com.bumptech.glide.Glide
import data.models.MovieBookingModel
import data.models.MovieBookingModelImpl
import delegates.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.toolbar
import utils.BASE_URL
import viewpods.MovieListViewPod

class HomeActivity : AppCompatActivity(), MovieViewHolderDelegate {
    companion object {

        private const val EXTRA_USER_EMAIL = "EXTRA_USER_EMAIL"
        private const val EXTRA_USER_PASSWORD = "EXTRA_USER_PASSWORD"
        fun newIntent(context: Context, email: String, password: String): Intent {
            val intent = Intent(context, HomeActivity::class.java)

            intent.putExtra(EXTRA_USER_EMAIL, email)
            intent.putExtra(EXTRA_USER_PASSWORD, password)
            return intent

        }
    }

    lateinit var mNowShowingViewPod: MovieListViewPod
    lateinit var mComingSoonViewPod: MovieListViewPod
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null


    //models
    private val mMovieModel: MovieModel = MovieModelImpl
    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupToolbar()
        setupViewPods()
        setupDrawer()
        requestData()

    }

    private fun requestData() {

        val email = intent.getStringExtra(EXTRA_USER_EMAIL)
        val password = intent?.getStringExtra(EXTRA_USER_PASSWORD)

        //get now playing
        mMovieModel.getNowPlayingMovies(
            onSuccess = {

                mNowShowingViewPod.setData(it)
            },
            onFailure = {
                ///show error message

            }
        )

        //get coming soon
        mMovieModel.getComingSoonMovies(
            onSuccess = {
                mComingSoonViewPod.setData(it)
            },
            onFailure = {
                //show error message
            }
        )
        // get profile
        mMovieBookingModel.getProfile(

            onSuccess = {
                Glide.with(this)
                    .load("$BASE_URL${it.profileImage}")
                    .into(ivProfile)
                tvName.text = "Hi " + it.name + "!"

                Glide.with(this)
                    .load("$BASE_URL${it.profileImage}")
                    .into(ivProfileDrawer)
                tvNameDrawer.text = "Hi " + it.name + "!"
                tvEmailDrawer.text = it.email


            },
            onFailure = {

            }
        )
        //logout
        llLogout.setOnClickListener {
            mMovieBookingModel.logoutUser(
                onSuccess = {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                },
                onFailure = {

                }
            )

        }


    }


    private fun setupDrawer() {

        setSupportActionBar(toolbar)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.string.lbl_drawerOpen,
                R.string.lbl_drawerClose
            )
        actionBarDrawerToggle?.let {
            drawerLayout.addDrawerListener(it)
            it.syncState()
        }
        toolbar.elevation = 0.0f
        supportActionBar?.elevation = 0.0f
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle?.onOptionsItemSelected(item) == true) {
            true
        } else super.onOptionsItemSelected(item)

    }


    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        //leading icon
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_black_24dp)


    }

    //search icon
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
    }

    private fun setupViewPods() {
        mNowShowingViewPod = vpNowShowing as MovieListViewPod
        mNowShowingViewPod.setupMovieViewPod(this)

        mComingSoonViewPod = vpComingSoon as MovieListViewPod
        mComingSoonViewPod.setupViewPods(getString(R.string.lbl_comingSoon))
        mComingSoonViewPod.setupMovieViewPod(this)

    }

    override fun onTapMovie(movieId: Int?) {
        val email = intent.getStringExtra(EXTRA_USER_EMAIL)
        val password = intent?.getStringExtra(EXTRA_USER_PASSWORD)
        startActivity(
            MovieDetailActivity.newIntent(
                this,
                movieId = movieId,
                email = email,
                password = password
            )
        )
    }


}