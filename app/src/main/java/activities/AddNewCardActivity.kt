package activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.attt.moviebookingapp.R
import data.models.MovieBookingModel
import data.models.MovieBookingModelImpl
import kotlinx.android.synthetic.main.activity_add_new_card.*

class AddNewCardActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
        fun newIntent(context: Context): Intent {
            return Intent(context, AddNewCardActivity::class.java)
        }
    }

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_card)

        setupListeners()


    }

    private fun setupListeners() {
        btnConfirm.setOnClickListener {

            mMovieBookingModel.createNewCard(
                cardHolder = etCardHolder.text.toString(),
                cardNumber = etCardNumber.text.toString(),
                expirationDate = etExpiration.text.toString(),
                cvc = etCVC.text.toString(),
                onSuccess = {
                    setResult(RESULT_OK)
                    finish()
                },
                onFailure = {

                }


            )

        }
        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }
}