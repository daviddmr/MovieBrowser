package david.com.moviebrowser.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import david.com.moviebrowser.R
import david.com.moviebrowser.model.Movie
import david.com.moviebrowser.util.Constants.Companion.POSTERS_BASE_URL

class MovieTopRatedAdapter(
        private val context: Context,
        private var movies: MutableList<Movie>,
        private val onClickListener: View.OnClickListener) : RecyclerView.Adapter<MovieTopRatedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_item_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val movie = movies[position]

        val urlMoviePoster = POSTERS_BASE_URL + movie.posterPath

        Picasso
                .with(context)
                .load(urlMoviePoster)
                .into(holder?.ivMoviePoster, object : Callback {
                    override fun onSuccess() {
                        holder?.pbImage?.visibility = View.GONE
                    }

                    override fun onError() {}
                })

        holder?.tvMovieTitle?.text = movie.title
        holder?.tvMovieNote?.text = movie.voteAverage.toString()

        holder?.mainLayout?.setOnClickListener(onClickListener)
        holder?.mainLayout?.tag = position

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun addMovieItems(newMovies: List<Movie>) {
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mainLayout: RelativeLayout = itemView.findViewById(R.id.mainLayout)
        var ivMoviePoster: ImageView = itemView.findViewById(R.id.ivMoviePoster)
        var pbImage: ProgressBar = itemView.findViewById(R.id.pbImage)
        var tvMovieTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)
        var tvMovieNote: TextView = itemView.findViewById(R.id.tvMovieNote)
    }
}