package adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.attt.moviebookingapp.R
import data.vos.DateVO
import data.vos.MovieVO
import data.vos.TimeslotsVO
import delegates.TimeslotsActionDelegate
import viewholders.MovieTimeViewHolder

class MovieTimeAdapter(private val mDelegate:TimeslotsActionDelegate): RecyclerView.Adapter<MovieTimeViewHolder>() {
    private var mTimeslotList:List<TimeslotsVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTimeViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_time,parent,false)
        return MovieTimeViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: MovieTimeViewHolder, position: Int) {
        if (mTimeslotList.isNotEmpty()){
            holder.bindData(mTimeslotList[position])
        }

    }

    override fun getItemCount(): Int {
        return mTimeslotList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(timeslotList:List<TimeslotsVO>){
        mTimeslotList=timeslotList
        notifyDataSetChanged()

    }
}