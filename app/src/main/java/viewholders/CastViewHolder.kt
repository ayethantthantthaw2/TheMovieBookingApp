package viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import data.vos.ActorVO
import kotlinx.android.synthetic.main.view_holder_cast.view.*
import utils.IMAGE_BASE_URL

class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(actors: ActorVO) {
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${actors.profile_path}")
            .into(itemView.ivCast)
        itemView.tvCastName.text=actors.name

    }
}