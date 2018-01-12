package david.com.moviebrowser.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

class MovieResponse(
        val page: Int,
        @SerializedName("total_results") val totalResults: Long,
        @SerializedName("total_pages") val totalPages: Long,
        @SerializedName("results") val movies: List<Movie>
)

@RealmClass
open class Movie : RealmObject() {

    @PrimaryKey
    var id: Long = 0

    var title: String = ""

    @SerializedName("vote_count")
    var voteCount: Double = 0.0

    var video: Boolean = false

    @SerializedName("vote_average")
    var voteAverage: Double = 0.0

    var popularity: Double = 0.0

    @SerializedName("poster_path")
    var posterPath: String = ""

    @SerializedName("original_language")
    var originalLanguage: String = ""

    @SerializedName("original_title")
    var originalTitle: String = ""

    @SerializedName("genre_ids")
    lateinit var genreIds: RealmList<Int>

    @SerializedName("backdrop_path")
    var backdropPath: String = ""

    var adult: Boolean = false

    var overview: String = ""
    @SerializedName("release_date")
    var releaseDate: String = ""

}