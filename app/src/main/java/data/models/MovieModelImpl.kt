package com.attt.themovieapp.data.models


import android.content.Context
import persistence.MovieBookingDatabase
import android.util.Log
import data.models.MovieBookingModelImpl
import data.vos.*
import network.dataAgents.MovieDataAgent
import network.dataAgents.MovieListDataAgentImpl

object MovieModelImpl : MovieModel {

    private val mMovieDataAgent: MovieDataAgent = MovieListDataAgentImpl

    //database
    private var mMovieDatabase: MovieBookingDatabase? = null

    fun initDatabase(context: Context) {
        mMovieDatabase = MovieBookingDatabase.getDBInstance(context)
    }
    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //database
        val data=mMovieDatabase?.movieDao()?.getMoviesByType(type = NOW_PLAYING)
        onSuccess(data?: listOf())

        mMovieDataAgent.getNowPlayingMovies(onSuccess = {

                //persistence layer
                it.forEach { movie -> movie.type = NOW_PLAYING }
                  mMovieDatabase?.movieDao()?.insertMovies(it)

                //to view layer
                onSuccess(it)
                Log.d("TAG", "getNowPlayingMovies:$it ")
                Log.d("Tag","${mMovieDatabase?.movieDao()?.getALLMovies()}")

        }, onFailure = {
            Log.d("TAG", "getNowPlayingMovies:$it ")
        })
    }

    override fun getComingSoonMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //database
        onSuccess(mMovieDatabase?.movieDao()?.getMoviesByType(type = COMING_SOON) ?: listOf())

        mMovieDataAgent.geComingSoonMovies(onSuccess = {
            //persistence layer
            it.forEach { movie -> movie.type = COMING_SOON }
            mMovieDatabase?.movieDao()?.insertMovies(it)
            Log.d("TAG", "getNowPlayingMovies:$it ")

            //to view layer
            onSuccess(it)



        }, onFailure = {
            Log.d("TAG", "Failure:$it ")
        })
    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgent.getGenres(onSuccess = onSuccess, onFailure = onFailure)
    }

    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //database
        val movieFromDatabase= mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
        movieFromDatabase?.let {
            //to view layer
            onSuccess(it)
        }
        mMovieDataAgent.getMovieDetails(
            onSuccess = {
                //insert data from network to persistence
                val movieFromDatabase= mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
                it.type=movieFromDatabase?.type
                mMovieDatabase?.movieDao()?.insertSingleMovie(it)

                //to view layer
                onSuccess(it)
            },
            onFailure = onFailure,
            movieId = movieId
        )
    }


    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgent.getCreditsByMovie(
            onSuccess = onSuccess,
            onFailure = onFailure,
            movieId = movieId
        )
    }

}
