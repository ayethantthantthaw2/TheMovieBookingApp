package viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import data.vos.MovieVO
import delegates.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.view_holder_movie.view.*
import utils.IMAGE_BASE_URL


class MovieViewHolder(itemView: View, mDelegate: MovieViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mMovie:MovieVO? = null

    fun bindData(movie: MovieVO) {
        mMovie=movie
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.ivMovie)
        itemView.tvMovieName.text=movie.title
        //itemView.tvMovieGenre.text=movie.


    }



    init {
        itemView.setOnClickListener {
            mMovie?.let {movie->
                mDelegate.onTapMovie(movie.id)
            }

        }

    }
}