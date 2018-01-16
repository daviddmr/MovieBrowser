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

//    @SerializedName("genre_ids")
//    var genreIds: RealmList<Int> = RealmList()

    @SerializedName("backdrop_path")
    var backdropPath: String = ""

    var adult: Boolean = false

    var overview: String = ""
    @SerializedName("release_date")
    var releaseDate: String = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        title = parcel.readString()
        voteCount = parcel.readDouble()
        video = parcel.readByte() != 0.toByte()
        voteAverage = parcel.readDouble()
        popularity = parcel.readDouble()
        posterPath = parcel.readString()
        originalLanguage = parcel.readString()
//        this.genreIds = RealmList()
//        parcel.readList(this.genreIds, Long::class.java.classLoader)
        originalTitle = parcel.readString()
        backdropPath = parcel.readString()
        adult = parcel.readByte() != 0.toByte()
        overview = parcel.readString()
        releaseDate = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeDouble(voteCount)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeDouble(voteAverage)
        parcel.writeDouble(popularity)
        parcel.writeString(posterPath)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalTitle)
//        parcel.writeList(genreIds)
        parcel.writeString(backdropPath)
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }


}