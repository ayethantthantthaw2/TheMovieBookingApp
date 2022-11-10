package network

import com.attt.themovieapp.network.responses.GetCreditsByMovieResponse
import com.attt.themovieapp.network.responses.GetGenreResponse
import data.vos.MovieVO
import network.response.MovieListResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import utils.*

interface TheMovieApi {
    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovie(
        @Query(PARAM_API_KEY) apikey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Call<MovieListResponse>

    @GET(API_GET_COMING_SOON)
    fun getComingSoonMovies(
        @Query(PARAM_API_KEY) apikey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Call<MovieListResponse>

    @GET(API_GET_GENRE)
    fun gerGenres(
        @Query(PARAM_API_KEY) apikey: String = MOVIE_API_KEY,
    ): Call<GetGenreResponse>

    @GET("$API_GET_MOVIE_DETAIL/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: String?,
        @Query(PARAM_API_KEY) apikey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Call<MovieVO>

    @GET("$API_GET_CREDITS_BY_MOVIE/{movie_id}/credits")
    fun getCreditsByMovie(
        @Path("movie_id") movieId: String?,
        @Query(PARAM_API_KEY) apikey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1,
    ): Call<GetCreditsByMovieResponse>


}