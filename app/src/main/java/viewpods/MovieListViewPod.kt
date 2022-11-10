package viewpods

import adapters.MovieAdapter
import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import data.vos.MovieVO
import delegates.MovieViewHolderDelegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var mMovieAdapter: MovieAdapter
    lateinit var mDelegate:MovieViewHolderDelegate



    override fun onFinishInflate() {
        //setupMovieListRecyclerView()
        super.onFinishInflate()
    }
    fun setData(movieList:List<MovieVO>){
        mMovieAdapter.setNewData(movieList)
    }

    fun setupViewPods(titleText:String) {
        tvMovieListTitle.text=titleText

    }
    fun setupMovieViewPod(delegate: MovieViewHolderDelegate){
        setUpDelegate(delegate)
        setupMovieListRecyclerView()
    }
    private fun setUpDelegate(delegate: MovieViewHolderDelegate){
        this.mDelegate=delegate

    }

    private fun setupMovieListRecyclerView() {
        mMovieAdapter = MovieAdapter(mDelegate)
        rvMovieList.adapter = mMovieAdapter
        rvMovieList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }



}