package viewholders

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.attt.moviebookingapp.R
import data.vos.DateVO
import delegates.DateActionDelegate
import delegates.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.view_holder_date.*
import kotlinx.android.synthetic.main.view_holder_date.view.*
import java.time.LocalDateTime


class DateViewHolder(itemView: View, mDelegate: DateActionDelegate) :
    RecyclerView.ViewHolder(itemView) {
    private var mDate: DateVO? = null
    fun bindData(date: DateVO) {
        mDate = date
        itemView.tvDay.text = date.dayOfWeek
        itemView.tvDate.text = date.day

        if (date.isSelected == true) {
            itemView.tvDay.setTextColor(
                ContextCompat.getColor(itemView.tvDay.context, R.color.white)
            )
            itemView.tvDate.setTextColor(
                ContextCompat.getColor(itemView.tvDate.context, R.color.white)
            )

        }else{
            itemView.tvDay.setTextColor(
                ContextCompat.getColor(itemView.tvDay.context, R.color.gray)
            )
            itemView.tvDate.setTextColor(
                ContextCompat.getColor(itemView.tvDate.context, R.color.gray)
            )
        }

        date.isSelected=false

    }

    init {
        itemView.setOnClickListener {
            mDate?.let {
                mDelegate.onTapDate(it.date)

            }
        }
    }

}