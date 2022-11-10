package network.dataAgents

import android.util.Log
import android.widget.Toast
import com.attt.themovieapp.network.responses.GetCreditsByMovieResponse
import com.attt.themovieapp.network.responses.GetGenreResponse
import data.vos.ActorVO
import data.vos.GenreVO
import data.vos.MovieVO
import network.response.MovieListResponse
import network.TheMovieApi
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utils.BASE_URL2
import java.util.concurrent.TimeUnit

object MovieListDataAgentImpl : MovieDataAgent {
    private var mTheMovieApi: TheMovieApi? = null

    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mTheMovieApi = retrofit.create(TheMovieApi::class.java)

    }

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getNowPlayingMovie()
            ?.enqueue(object : Callback<MovieListResponse> {
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    if (response.isSuccessful) {
                        val movieList = response.body()?.results ?: listOf()
                        onSuccess(movieList)

                    } else {
                        onFailure(response.message())

                    }

                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.message}")
                }
            })
    }

    override fun geComingSoonMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getComingSoonMovies()
            ?.enqueue(object : Callback<MovieListResponse> {
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    if (response.isSuccessful) {
                        val movieList = response.body()?.results ?: listOf()
                        onSuccess(movieList)

                    } else {
                        onFailure(response.message())

                    }

                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {

                }
            })

    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        mTheMovieApi?.gerGenres()
            ?.enqueue(object : Callback<GetGenreResponse> {
                override fun onResponse(
                    call: Call<GetGenreResponse>,
                    response: Response<GetGenreResponse>
                ) {
                    if (response.isSuccessful) {
                        val genreList = response.body()?.genres ?: listOf()
                        onSuccess(genreList)

                    } else {
                        onFailure(response.message())

                    }

                }

                override fun onFailure(call: Call<GetGenreResponse>, t: Throwable) {

                }
            })

    }
//
    override fun getMovieDetails(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getMovieDetail(movieId=movieId)
            ?.enqueue(object : Callback<MovieVO> {
                override fun onResponse(
                    call: Call<MovieVO>,
                    response: Response<MovieVO>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(it)
                        }

                    } else {
                        onFailure(response.message())

                    }

                }

                override fun onFailure(call: Call<MovieVO>, t: Throwable) {

                }
            })

    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getCreditsByMovie(movieId=movieId)
            ?.enqueue(object : Callback<GetCreditsByMovieResponse> {
                override fun onResponse(
                    call: Call<GetCreditsByMovieResponse>,
                    response: Response<GetCreditsByMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onSuccess(it.cast)
                        }

                    } else {
                        onFailure(response.message())

                    }

                }

                override fun onFailure(call: Call<GetCreditsByMovieResponse>, t: Throwable) {

                }
            })

    }
}