package david.com.moviebrowser.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
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

    companion object {
        val TAG = "MovieFrag"

        fun newInstance(): MovieFragment {
            return MovieFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadMovies()
    }

    fun initRecyclerView(movies: List<Movie>) {
        rvTopRatedMovies.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(context)
        rvTopRatedMovies.layoutManager = linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        rvTopRatedMovies.addItemDecoration(dividerItemDecoration)

        val movieTopRatedAdapter = MovieTopRatedAdapter(context, movies)
        rvTopRatedMovies.adapter = movieTopRatedAdapter
    }

    private fun loadMovies() {

        val realm = Realm.getDefaultInstance()

        val call = RetrofitInitializer().movieService().getTopRatedMovies(1, API_KEY, language)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                Log.d("EX", "")
                val movies: List<Movie> = response?.body()?.movies ?: listOf()
                movies.isNotEmpty().let {
                    initRecyclerView(movies)
                    realm.executeTransaction {
                        realm.copyToRealmOrUpdate(movies)
                    }
                }

                val moviesQueried: List<Movie> = realm.copyFromRealm(realm.where(Movie::class.java).findAll())
                Log.d("EX", "")
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.d("EX", "")
            }

        })

    }
}
