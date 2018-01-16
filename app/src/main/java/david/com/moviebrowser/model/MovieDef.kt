package david.com.moviebrowser.model

import android.os.Parcel
import android.os.Parcelable
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
open class Movie() : RealmObject(), Parcelable {
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
    var genreIds: RealmList<Int> = RealmList()

    @SerializedName("backdrop_path")
    var backdropPath: String = ""

    var adult: Boolean = false

    var overview: String = ""

    @SerializedName("release_date")
    var releaseDate: String = ""

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
        }
    }

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        title = parcel.readString()
        voteCount = parcel.readDouble()
        video = 1 == parcel.readInt()
        voteAverage = parcel.readDouble()
        popularity = parcel.readDouble()
        posterPath = parcel.readString()
        originalLanguage = parcel.readString()
        originalTitle = parcel.readString()
        parcel.readList(genreIds, List::class.java.classLoader)
        backdropPath = parcel.readString()
        adult = 1 == parcel.readInt()
        overview = parcel.readString()
        releaseDate = parcel.readString()
    }


    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(title)
        writeDouble(voteCount)
        writeInt((if (video) 1 else 0))
        writeDouble(voteAverage)
        writeDouble(popularity)
        writeString(posterPath)
        writeString(originalLanguage)
        writeString(originalTitle)
        writeList(genreIds)
        writeString(backdropPath)
        writeInt((if (adult) 1 else 0))
        writeString(overview)
        writeString(releaseDate)
    }
}