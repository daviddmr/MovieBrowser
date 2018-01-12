package david.com.moviebrowser.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

class GenreResponse(val genres: List<Genre>)

@RealmClass
open class Genre : RealmObject() {

    @PrimaryKey
    var id: Long = 0

    var name: String = ""

}