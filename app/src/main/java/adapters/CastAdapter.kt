package adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.attt.moviebookingapp.R
import data.vos.ActorVO
import viewholders.CastViewHolder

class CastAdapter: RecyclerView.Adapter<CastViewHolder>() {
    private var mActors:List<ActorVO> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_cast,parent,false)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        if (mActors.isNotEmpty()){
            holder.bindData(mActors[position])
        }

    }

    override fun getItemCount(): Int {
        return mActors.count()
    }

    @SuppressLint("notifyDataSetChanged")
    fun setNewData(actors: List<ActorVO>) {
        mActors=actors
        notifyDataSetChanged()
    }
}