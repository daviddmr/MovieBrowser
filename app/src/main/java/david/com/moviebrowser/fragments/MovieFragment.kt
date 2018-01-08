package david.com.moviebrowser.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import david.com.moviebrowser.R

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
}
