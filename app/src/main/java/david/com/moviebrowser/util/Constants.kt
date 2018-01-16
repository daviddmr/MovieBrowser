package david.com.moviebrowser.util

import java.util.*

class Constants {

    companion object {
        //API Headers
        const val GRANT_TYPE_PASSWORD = "password"
        const val SCOPE_WRITE = "write"
        const val HEADER_CONTENT_TYPE = "Content-Type"
        const val FORM_URL_ENCODED = "application/x-www-form-urlencoded"
        const val HEADER_ACCEPT = "Accept"
        const val APPLICATION_JSON = "application/json"
        const val HEADER_AUTHORIZATION = "Authorization"

        //API Constants
        const val BASE_URL: String = "https://api.themoviedb.org/"
        const val POSTERS_BASE_URL: String = "http://image.tmdb.org/t/p/w154"
        const val BACK_DROPS_BASE_URL: String = "http://image.tmdb.org/t/p/w300"
        const val API_VERSION_3: String = "3/"
        const val API_VERSION_4: String = "4/"
        const val API_KEY: String = "0c38011bd9f7accb307c3208ef59af04"
        val language: String = Locale.getDefault().toString().replace("_", "-")

        const val movie: String = "movie/"
        const val genre: String = "genre/"
    }

}