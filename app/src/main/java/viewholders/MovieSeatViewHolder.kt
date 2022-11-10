package viewholders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.attt.moviebookingapp.R
import data.vos.MovieSeatVO
import data.vos.TimeslotsVO
import delegates.SeatActionDelegate
import kotlinx.android.synthetic.main.view_holder_movie_seat.view.*
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieSeatViewHolder(itemView: View, mDelegate: SeatActionDelegate) :
    RecyclerView.ViewHolder(itemView) {
    private var mMovieSeat: MovieSeatVO? = null
    fun bindData(data: MovieSeatVO) {
        mMovieSeat = data

        when {
            data.isMovieSeatAvailable() -> {
                itemView.tvMovieSeatTitle.text = data.seatName
                itemView.background = ContextCompat.getDrawable(
                    itemView.context, R.drawable.background_movie_seat_available
                )
                if (data.isSelected) {
                    itemView.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.background_seat_selected
                        )
                    )
                    itemView.tvMovieSeatTitle.setTextColor(
                        ContextCompat.getColor(itemView.context, R.color.white)
                    )
                } else {
                    itemView.setBackgroundDrawable(
                        ContextCompat.getDrawable(
                            itemView.context,
                            R.drawable.background_movie_seat_available
                        )
                    )
                    itemView.tvMovieSeatTitle.setTextColor(
                        ContextCompat.getColor(itemView.context, R.color.white)
                    )
                }


            }
            data.isMovieSeatTaken() == true -> {
                itemView.tvMovieSeatTitle.text = data.seatName
                itemView.background = ContextCompat.getDrawable(
                    itemView.context, R.drawable.background_movie_seat_taken
                )
            }
            data.isMovieSeatRowTitle() == true -> {
                itemView.tvMovieSeatTitle.text = data.symbol
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context, R.color.white
                    )
                )

            }
            else -> {
                itemView.tvMovieSeatTitle.visibility = View.GONE
                itemView.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context, R.color.white
                    )
                )
            }

        }

    }

    init {
        itemView.setOnClickListener {
            mMovieSeat?.let {
                mDelegate.onTapSeat(it.seatName)
            }
        }
    }
}