package david.com.moviebrowser.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import david.com.moviebrowser.R
import david.com.moviebrowser.model.Movie

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        val TAG = "MovieDetailAct"
        val ARG_MOVIE = "arg_movie"
    }

    lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        intent.let {
            movie = it.getParcelableExtra(ARG_MOVIE)
            Log.d(TAG, "")
        }

    }
}
