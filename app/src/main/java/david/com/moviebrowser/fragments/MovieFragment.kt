package david.com.moviebrowser.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import david.com.moviebrowser.R
import david.com.moviebrowser.api.RetrofitInitializer
import david.com.moviebrowser.model.ResponseBody
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

    fun loadMovies() {

        val call = RetrofitInitializer().githubService().getAllMovies(2, 1, null)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>) {
                Log.d("EX", "")
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.d("EX", "")
            }

        })

    }
}
