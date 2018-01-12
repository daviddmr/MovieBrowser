package david.com.moviebrowser.api.interfaces

import david.com.moviebrowser.model.GenreResponse
import david.com.moviebrowser.util.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {

    @GET(Constants.API_VERSION_3 + Constants.genre + "movie/list")
    fun getGenres(
            @Query("api_key") apiKey: String,
            @Query("language") language: String?
    ): Call<GenreResponse>

}