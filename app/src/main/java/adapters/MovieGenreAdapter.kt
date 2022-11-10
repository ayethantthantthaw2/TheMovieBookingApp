package adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.attt.moviebookingapp.R
import data.vos.GenreVO
import viewholders.MovieGenreViewHolder

class MovieGenreAdapter : RecyclerView.Adapter<MovieGenreViewHolder>() {
    private var mGenreList: List<GenreVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGenreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_movie_genre, parent, false)
        return MovieGenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieGenreViewHolder, position: Int) {
        if (mGenreList.isNotEmpty()) {
            holder.binData(mGenreList[position])

        }

    }

    override fun getItemCount(): Int {
        return mGenreList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(genreList: List<GenreVO>) {
        mGenreList = genreList
        notifyDataSetChanged()

    }
}