package david.com.moviebrowser.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import david.com.moviebrowser.R
import david.com.moviebrowser.model.Movie

class MovieTopRatedAdapter(
        private val context: Context,
        private val movies: List<Movie>) : RecyclerView.Adapter<MovieTopRatedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_item_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val movie = movies[position]
        holder?.tvMovieId?.text = movie.id.toString()
        holder?.tvMovieTitle?.text = movie.title
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvMovieTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)
        var tvMovieId: TextView = itemView.findViewById(R.id.tvMovieId)
    }
}