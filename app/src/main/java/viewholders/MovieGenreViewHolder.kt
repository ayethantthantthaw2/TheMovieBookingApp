package viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import data.vos.GenreVO
import data.vos.MovieVO
import kotlinx.android.synthetic.main.view_holder_movie_genre.view.*

class MovieGenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var mGenre: GenreVO? = null

    fun binData(genre: GenreVO) {
        mGenre = genre
        itemView.tvGenre.text = genre.name


    }


}