package activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.attt.moviebookingapp.R
import com.attt.themovieapp.data.models.MovieModel
import com.attt.themovieapp.data.models.MovieModelImpl
import com.bumptech.glide.Glide
import data.models.MovieBookingModel
import data.models.MovieBookingModelImpl
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_welcome.*
import utils.BASE_URL

class WelcomeActivity : AppCompatActivity() {
    //models

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        login()
        requestData()
    }

    private fun requestData() {
        mMovieBookingModel.getProfileForWelcome(
            onSuccess = {
                if (it.token != null) {

                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
                else{
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
            }, onFailure = {

            })
    }

    private fun login() {
        btnGetStarted.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}