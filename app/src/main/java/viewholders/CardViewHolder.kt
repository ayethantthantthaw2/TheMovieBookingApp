package viewholders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.attt.moviebookingapp.R
import com.bumptech.glide.Glide
import data.vos.PaymentMethodVO
import delegates.PaymentMethodActionDelegate
import kotlinx.android.synthetic.main.view_holder_card.view.*
import kotlinx.android.synthetic.main.view_holder_movie.view.*
import utils.IMAGE_BASE_URL

class CardViewHolder(itemView: View,mDelegate:PaymentMethodActionDelegate) : RecyclerView.ViewHolder(itemView) {
    private var mPaymentMethod: PaymentMethodVO? = null
    fun bindData(paymentMethod: PaymentMethodVO) {
        mPaymentMethod = paymentMethod
        itemView.tvCardName.text = paymentMethod.name
        itemView.tvDescription.text = paymentMethod.description
        if (paymentMethod.isSelected) {
            Glide.with(itemView.context)
                .load(R.drawable.ic_baseline_credit_card_24)
                .into(itemView.ivCard)
            itemView.tvCardName.setTextColor(
                ContextCompat.getColor(itemView.context, R.color.colorPrimary)
            )
            itemView.tvDescription.setTextColor(
                ContextCompat.getColor(itemView.context, R.color.colorPrimary)
            )

        } else {
            Glide.with(itemView.context)
                .load(R.drawable.ic_baseline_credit_card_white_24dp)
                .into(itemView.ivCard)
            itemView.tvCardName.setTextColor(
                ContextCompat.getColor(itemView.context, R.color.black)
            )
            itemView.tvDescription.setTextColor(
                ContextCompat.getColor(itemView.context, R.color.black)
            )
        }
        paymentMethod.isSelected=false
    }
    init {
        itemView.setOnClickListener {
            mPaymentMethod?.let {id->
                mDelegate.onTapPaymentMethod(id.id)
            }

        }

    }
}