package adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.attt.moviebookingapp.R
import data.vos.PaymentMethodVO
import data.vos.SnackVO
import delegates.MovieViewHolderDelegate
import delegates.PaymentMethodActionDelegate
import viewholders.CardViewHolder

class CardAdapter(private val mDelegate: PaymentMethodActionDelegate): RecyclerView.Adapter<CardViewHolder>() {
    private var mPaymentMethodList:List<PaymentMethodVO> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_card, parent, false)
        return CardViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        if (mPaymentMethodList.isNotEmpty()){
            holder.bindData(mPaymentMethodList[position])
        }

    }

    override fun getItemCount(): Int {
        return 3
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(paymentMethodList: List<PaymentMethodVO>) {
        mPaymentMethodList = paymentMethodList
        notifyDataSetChanged()
    }
}