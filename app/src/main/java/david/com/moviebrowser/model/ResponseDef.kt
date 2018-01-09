package david.com.moviebrowser.model

class ResponseBody(
        val status: String,
        val status_message: String,
        val data: Data
)

class Data(
        val movie_count: Long,
        val limit: Long,
        val page_number: Long,
        val movies: List<Movie>
)

class Movie(
        val id: Long?,
        val url: String?
)