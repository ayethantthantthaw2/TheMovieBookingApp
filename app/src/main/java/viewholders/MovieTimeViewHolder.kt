package viewholders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.attt.moviebookingapp.R
import data.vos.TimeslotsVO
import delegates.TimeslotsActionDelegate
import kotlinx.android.synthetic.main.view_holder_date.view.*
import kotlinx.android.synthetic.main.view_holder_movie_time.view.*

class MovieTimeViewHolder(itemView: View, mDelegate: TimeslotsActionDelegate) :
    RecyclerView.ViewHolder(itemView) {
    private var mTimeslots: TimeslotsVO? = null

    fun bindData(timeslots: TimeslotsVO) {
        mTimeslots = timeslots
        itemView.tvTimeslot.text = timeslots.startTime
        if (timeslots.isSelectedTimeslots == true) {

            itemView.rlTimeslot.background =
                ContextCompat.getDrawable(itemView.context, R.drawable.background_timeslot)

            itemView.tvTimeslot.setTextColor(
                ContextCompat.getColor(itemView.context, R.color.white)
            )

        } else {
            itemView.rlTimeslot.background =
                ContextCompat.getDrawable(itemView.context, R.drawable.background_movie_time)
            itemView.tvTimeslot.setTextColor(
                ContextCompat.getColor(itemView.context, R.color.black)
            )
        }
        timeslots.isSelectedTimeslots = false

    }

    init {

        itemView.setOnClickListener {
            mTimeslots?.let {
                mDelegate.onTapTimeSlot(it.cinemaDayTimeslotId)
            }
        }
    }

}