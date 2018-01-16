package david.com.moviebrowser.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import david.com.moviebrowser.R
import david.com.moviebrowser.activities.MovieDetailActivity
import david.com.moviebrowser.adapters.MovieTopRatedAdapter
import david.com.moviebrowser.api.RetrofitInitializer
import david.com.moviebrowser.api.interfaces.GenreService
import david.com.moviebrowser.api.interfaces.MovieService
import david.com.moviebrowser.model.Genre
import david.com.moviebrowser.model.GenreResponse
import david.com.moviebrowser.model.Movie
import david.com.moviebrowser.model.MovieResponse
import david.com.moviebrowser.util.Constants.Companion.API_KEY
import david.com.moviebrowser.util.Constants.Companion.language
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_movies.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieFragment : Fragment() {

    private var currentPage: Int = 1
    private var movies: MutableList<Movie> = mutableListOf()

    companion object {
        val TAG = "MovieFrag"

        fun newInstance(): MovieFragment {
            return MovieFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initAdapter()
        loadMovies(currentPage)
    }

    private fun initAdapter() {
        val movieTopRatedAdapter = MovieTopRatedAdapter(context, movies, onClickListener())
        rvTopRatedMovies.adapter = movieTopRatedAdapter
    }

    private fun initRecyclerView() {
        rvTopRatedMovies.setHasFixedSize(true)
        rvTopRatedMovies.addOnScrollListener(onScrollListener())
        val linearLayoutManager = GridLayoutManager(context, 4)
        rvTopRatedMovies.layoutManager = linearLayoutManager
    }

    fun refreshMovieList(newMovies: List<Movie>) {
        val adapter: MovieTopRatedAdapter = rvTopRatedMovies.adapter as MovieTopRatedAdapter
        adapter.addMovieItems(newMovies)
    }

    private fun loadGenres() {
        val realm = Realm.getDefaultInstance()

        val call = RetrofitInitializer().service(GenreService::class.java).getGenres(API_KEY, language)
        call.enqueue(object : Callback<GenreResponse> {
            override fun onResponse(call: Call<GenreResponse>?, response: Response<GenreResponse>?) {
                val genres = response?.body()?.genres ?: listOf()
                genres.isNotEmpty().let {
                    realm.executeTransaction {
                        realm.copyToRealm(genres)
                    }
                }

                val genresQueried: List<Genre> = realm.copyFromRealm(realm.where(Genre::class.java).findAll())
                Log.d(TAG, "")
            }

            override fun onFailure(call: Call<GenreResponse>?, t: Throwable?) {
                Log.d(TAG, "")
            }

        })
    }

    private fun loadMovies(currentPage: Int) {

        val realm = Realm.getDefaultInstance()

        val call = RetrofitInitializer().service(MovieService::class.java).getTopRatedMovies(currentPage, API_KEY, language)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>?, response: Response<MovieResponse>?) {
                Log.d(TAG, "Getting movie: " + currentPage)
                this@MovieFragment.currentPage++

                val newMovies = response?.body()?.movies as MutableList<Movie>
                newMovies.isNotEmpty().let {
                    refreshMovieList(newMovies)
                    realm.executeTransaction {
                        realm.copyToRealmOrUpdate(newMovies)
                    }
                }

                val moviesQueried: List<Movie> = realm.copyFromRealm(realm.where(Movie::class.java).findAll())
                Log.d(TAG, "")
            }

            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
                Log.d(TAG, "")
            }

        })

    }

    private fun onClickListener(): View.OnClickListener {
        return View.OnClickListener { v ->
            val position: Int = v.tag as Int
            openMovieDetail(movies[position])
        }
    }

    private fun openMovieDetail(movie: Movie) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.ARG_MOVIE, movie)
        startActivity(intent)
    }

    private fun onScrollListener(): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager: LinearLayoutManager = rvTopRatedMovies.layoutManager as LinearLayoutManager
                if (movies.size == linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) {
                    Log.d(TAG, "Get More!")
                    loadMovies(currentPage)
                }
            }
        }
    }
}
