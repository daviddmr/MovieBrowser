package david.com.moviebrowser.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import david.com.moviebrowser.R
import david.com.moviebrowser.adapters.MovieTopRatedAdapter
import david.com.moviebrowser.api.RetrofitInitializer
import david.com.moviebrowser.model.Movie
import david.com.moviebrowser.model.ResponseBody
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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        loadMovies(currentPage)
    }

    private fun initRecyclerView() {
        rvTopRatedMovies.setHasFixedSize(true)
        rvTopRatedMovies.addOnScrollListener(onScrollListener())
        val linearLayoutManager = LinearLayoutManager(context)
        rvTopRatedMovies.layoutManager = linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        rvTopRatedMovies.addItemDecoration(dividerItemDecoration)
    }

    fun refreshMovieList(movies: List<Movie>) {
        val movieTopRatedAdapter = MovieTopRatedAdapter(context, movies)
        rvTopRatedMovies.adapter = movieTopRatedAdapter
        movieTopRatedAdapter.notifyDataSetChanged()
    }

    private fun loadMovies(currentPage: Int) {

        val realm = Realm.getDefaultInstance()

        val call = RetrofitInitializer().movieService().getTopRatedMovies(currentPage, API_KEY, language)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                Log.d(TAG, "Getting movie: " + currentPage)
                this@MovieFragment.currentPage++

                movies.addAll(response?.body()?.movies as MutableList<Movie>)
                movies.isNotEmpty().let {
                    refreshMovieList(movies)
                    realm.executeTransaction {
                        realm.copyToRealmOrUpdate(movies)
                    }
                }

                val moviesQueried: List<Movie> = realm.copyFromRealm(realm.where(Movie::class.java).findAll())
                Log.d(TAG, "")
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.d(TAG, "")
            }

        })

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
