package activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.attt.moviebookingapp.R
import com.bumptech.glide.Glide
import com.google.gson.Gson
import data.vos.CheckOutVO
import data.vos.SelectedSnackVO
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_vouncher.*
import utils.BASE_URL
import utils.BASE_URL2
import utils.IMAGE_BASE_URL

class VoucherActivity : AppCompatActivity() {
    companion object {
        private const val EXTRA_CHECKOUT = "EXTRA_CHECKOUT"
        private const val EXTRA_POSTER = "EXTRA_POSTER"
        private const val EXTRA_MOVIE_NAME = "EXTRA_MOVIE_NAME"
        private const val EXTRA_CINEMA_NAME = "EXTRA_CINEMA_NAME"


        fun newIntent(
            context: Context,
            checkOut: String?,
            movieName:String?,
            poster:String?,
            cinemaName:String?
        ): Intent {
            val intent = Intent(context, VoucherActivity::class.java)
            intent.putExtra(EXTRA_CHECKOUT, checkOut)
            intent.putExtra(EXTRA_MOVIE_NAME, movieName)
            intent.putExtra(EXTRA_POSTER,poster)
            intent.putExtra(EXTRA_CINEMA_NAME,cinemaName)


            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vouncher)
        val checkOut = intent?.getStringExtra(EXTRA_CHECKOUT)

        val poster=intent.getStringExtra(EXTRA_POSTER)
        val movieName = intent?.getStringExtra(EXTRA_MOVIE_NAME)
        val cinemaName=intent?.getStringExtra(EXTRA_CINEMA_NAME)


        //convert string to object
        val checkoutList = Gson().fromJson(checkOut, CheckOutVO::class.java)
        tvBookingNo.text=checkoutList.bookingNo
        tvShowtimeDate.text=checkoutList.bookingDate
        tvTheaterName.text=cinemaName
        tvCinema.text=cinemaName
        tvMovieNameInTicket.text=movieName
        tvScreenNo.text=checkoutList.cinemaId.toString()
        tvRowNo.text=checkoutList.row
        tvSeatsNo.text=checkoutList.seat
        tvPriceAmount.text=checkoutList.total
        Glide.with(this)
            .load("$BASE_URL/${checkoutList.qrCode}")
            .into(ivQr)
        Glide.with(this)
            .load("$IMAGE_BASE_URL${poster}")
            .into(ivMovieInTicket)


        setupListener()
    }

    private fun setupListener() {
        btnCancel.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}