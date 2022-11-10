package adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.attt.moviebookingapp.R
import data.vos.MovieSeatVO
import delegates.SeatActionDelegate
import viewholders.MovieSeatViewHolder

class MovieSeatAdapter(val mDelegate:SeatActionDelegate): RecyclerView.Adapter<MovieSeatViewHolder>() {
    private var mMovieSeat:List<MovieSeatVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSeatViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_seat,parent,false)
        return MovieSeatViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: MovieSeatViewHolder, position: Int) {
        if (mMovieSeat.isNotEmpty()){
            holder.bindData(mMovieSeat[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovieSeat.count()
    }
        @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieSeat: List<MovieSeatVO>){
        mMovieSeat=movieSeat
        notifyDataSetChanged()

    }
}