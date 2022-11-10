package viewholders

import adapters.MovieTimeAdapter
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.attt.moviebookingapp.R
import data.vos.CinemaVO
import data.vos.MovieVO
import data.vos.TimeslotsVO
import delegates.TimeslotsActionDelegate
import kotlinx.android.synthetic.main.view_holder_cinema.view.*
import kotlinx.android.synthetic.main.view_holder_movie_time.view.*

class CinemaViewHolder(itemView: View,private val mDelegate:TimeslotsActionDelegate) : RecyclerView.ViewHolder(itemView) {
    private var mCinema: CinemaVO? = null

    private val rvMovieTime=itemView.findViewById<RecyclerView>(R.id.rvMovieTime)
    init {

    }
    fun bindData(cinema: CinemaVO) {
        val mMovieTimeAdapter= MovieTimeAdapter(mDelegate)
        rvMovieTime.adapter=mMovieTimeAdapter
        rvMovieTime.layoutManager=GridLayoutManager(itemView.context,3)
        mCinema=cinema
        itemView.tvCinema.text=cinema.cinema
        mMovieTimeAdapter.setNewData(cinema.timeslots)

//        for (i in cinema.timeslots)
//        itemView.tvTimeslot.text=cinema.timeslots[i.hashCode()].startTime





    }





}