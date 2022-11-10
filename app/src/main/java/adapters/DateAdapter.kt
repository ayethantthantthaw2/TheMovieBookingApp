package adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.attt.moviebookingapp.R
import data.vos.DateVO
import data.vos.MovieVO
import delegates.DateActionDelegate
import delegates.MovieViewHolderDelegate
import viewholders.DateViewHolder
import java.time.LocalDateTime

class DateAdapter(private val mDelegate: DateActionDelegate): RecyclerView.Adapter<DateViewHolder>() {
    private var mDateList:List<DateVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_date,parent,false)
        return DateViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        if (mDateList.isNotEmpty()){
            holder.bindData(mDateList[position])


        }

    }

    override fun getItemCount(): Int {
        return mDateList.count()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(dateList:List<DateVO>){
        mDateList=dateList
        notifyDataSetChanged()

    }

}