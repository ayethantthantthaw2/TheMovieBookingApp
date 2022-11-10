package adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.attt.moviebookingapp.R
import data.vos.MovieVO
import data.vos.SnackVO
import delegates.CountActionDelegate
import viewholders.ComboViewHolder

class ComboAdapter(private val mDelegate:CountActionDelegate): RecyclerView.Adapter<ComboViewHolder>() {
    private var mSnackList:List<SnackVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComboViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_combo,parent,false)
        return ComboViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: ComboViewHolder, position: Int) {
        if (mSnackList.isNotEmpty()){
            holder.bindData(mSnackList[position])
        }
    }

    override fun getItemCount(): Int {
        return mSnackList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(snackList: List<SnackVO>) {
        mSnackList=snackList
        notifyDataSetChanged()


    }
}