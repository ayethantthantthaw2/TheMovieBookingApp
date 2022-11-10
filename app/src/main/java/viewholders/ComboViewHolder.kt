package viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import data.vos.MovieVO
import data.vos.SnackVO
import delegates.CountActionDelegate
import kotlinx.android.synthetic.main.view_holder_combo.view.*

class ComboViewHolder(itemView: View, mDelegate: CountActionDelegate) :
    RecyclerView.ViewHolder(itemView) {
    private var mSnackList: SnackVO? = null
    fun bindData(snackList: SnackVO) {
        mSnackList = snackList
        itemView.tvComboTitle.text = snackList.name
        itemView.tVCount.text=snackList.qty.toString()
        itemView.tvComboSet.text = snackList.description
        itemView.tvSnackPrice.text = snackList.price.toString() + "$"

    }

    init {
        itemView.tvAdd.setOnClickListener {
            mSnackList?.let { id ->
                mDelegate.onTapPlus(id.id)
            }

        }
        itemView.tvMinus.setOnClickListener {
            mSnackList?.let { id ->
                mDelegate.onTapMinus(id.id)
            }

        }

    }
}