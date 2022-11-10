package adapters

import alirezat775.lib.carouselview.CarouselAdapter
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.helper.widget.Carousel
import androidx.core.content.ContextCompat
import com.attt.moviebookingapp.R
import com.bumptech.glide.Glide
import data.vos.CardVO
import data.vos.MovieVO
import kotlinx.android.synthetic.main.view_holder_carousel_view.view.*
import kotlinx.android.synthetic.main.view_holder_movie.view.*
import utils.IMAGE_BASE_URL

class CarouselViewAdapter : CarouselAdapter() {
    private var mCardList: List<CardVO> = listOf()

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        if (mCardList.isNotEmpty()) {
            holder.bindData(mCardList[position])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_carousel_view, parent, false)
        return CarouselViewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mCardList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(cards: List<CardVO>) {
        mCardList = cards
        notifyDataSetChanged()
    }

    inner class CarouselViewViewHolder(itemView: View) : CarouselViewHolder(itemView) {}


}

lateinit var mCard: CardVO
private fun CarouselAdapter.CarouselViewHolder.bindData(cardVO: CardVO) {
    mCard = cardVO
    itemView.tvCardNumber.text = cardVO.cardNumber
    itemView.tvCardHolderName.text = cardVO.cardHolder
    itemView.tvCvc.text=cardVO.cardType
    itemView.tvExpiredDate.text = cardVO.expirationDate
    if (cardVO.isSelected) {
        itemView.setBackgroundColor(
            ContextCompat.getColor(
                itemView.context, R.color.purple_500
            )
        )
    } else {
        itemView.setBackgroundColor(
            ContextCompat.getColor(
                itemView.context, R.color.colorPrimary
            )
        )
    }
    cardVO.isSelected = false

}
