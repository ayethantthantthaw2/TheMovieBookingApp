package adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.attt.moviebookingapp.R
import data.vos.CinemaVO
import data.vos.TimeslotsVO
import delegates.TimeslotsActionDelegate
import viewholders.CinemaViewHolder

class CinemaAdapter(private val mDelegate:TimeslotsActionDelegate) : RecyclerView.Adapter<CinemaViewHolder>() {
    private var mCinemaList:List<CinemaVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_cinema, parent, false)
        return CinemaViewHolder(view,mDelegate)

    }

    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
        if (mCinemaList.isNotEmpty()){
            holder.bindData(mCinemaList[position])
        }

    }

    override fun getItemCount(): Int {
        return mCinemaList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(cinemaList: List<CinemaVO>) {
        mCinemaList = cinemaList
        notifyDataSetChanged()
    }

    }