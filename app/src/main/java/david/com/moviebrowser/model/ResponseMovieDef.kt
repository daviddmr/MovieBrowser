package david.com.moviebrowser.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

class ResponseBody(
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

}