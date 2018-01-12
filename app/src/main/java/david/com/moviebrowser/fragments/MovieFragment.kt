package david.com.moviebrowser.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import david.com.moviebrowser.R
import david.com.moviebrowser.api.RetrofitInitializer
import david.com.moviebrowser.model.Movie
import david.com.moviebrowser.model.ResponseBody
import david.com.moviebrowser.util.Constants.Companion.API_KEY
import david.com.moviebrowser.util.Constants.Companion.language
import io.realm.Realm
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

    private fun loadMovies() {

        val realm = Realm.getDefaultInstance()

        val call = RetrofitInitializer().movieService().getTopRatedMovies(1, API_KEY, language)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>) {
                Log.d("EX", "")
                realm.beginTransaction()
                realm.copyToRealmOrUpdate(response.body()?.movies)
                realm.commitTransaction()

                val movie = realm.where(Movie::class.java).findAll()
                val movie2 = realm.copyFromRealm(movie)
                Log.d("EX", "")
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.d("EX", "")
            }

        })

    }
}
