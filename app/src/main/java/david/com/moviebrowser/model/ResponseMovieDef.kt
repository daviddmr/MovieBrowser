package david.com.moviebrowser.model

import com.google.gson.annotations.SerializedName

class ResponseBody(
        val page: Int,
        @SerializedName("total_results") val totalResults: Long,
        @SerializedName("total_pages") val totalPages: Long,
        @SerializedName("results") val movies: List<Movie>
)

class Movie(
        val id: Long?,
        val title: String?
)