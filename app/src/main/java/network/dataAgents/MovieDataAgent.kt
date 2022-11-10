package network.dataAgents


import data.vos.ProductionCompanyVO
import data.vos.ActorVO
import data.vos.GenreVO
import data.vos.MovieVO

interface MovieDataAgent {
    fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>)->Unit,
        onFailure: (String)->Unit
    )

    fun geComingSoonMovies(
        onSuccess: (List<MovieVO>)->Unit,
        onFailure: (String)->Unit
    )

    fun getGenres(
        onSuccess: (List<GenreVO>)->Unit,
        onFailure: (String)->Unit
    )

    fun getMovieDetails(
        movieId:String,
        onSuccess: (MovieVO)->Unit,
        onFailure: (String)->Unit
    )

    fun getCreditsByMovie(
        movieId:String,
        onSuccess: (List<ActorVO>)->Unit,
        onFailure: (String)->Unit
    )


}