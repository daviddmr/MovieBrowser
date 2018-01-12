package david.com.moviebrowser.util

import java.util.*

class Constants {

    companion object {
        //API Constants
        const val BASE_URL: String = "https://api.themoviedb.org/"
        const val API_VERSION_3: String = "3/"
        const val API_VERSION_4: String = "4/"
        const val API_KEY: String = "0c38011bd9f7accb307c3208ef59af04"
        val language: String = Locale.getDefault().toString().replace("_", "-")

        const val movie: String = "movie/"
    }

}