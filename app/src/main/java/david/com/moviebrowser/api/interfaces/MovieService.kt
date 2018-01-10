package david.com.moviebrowser.api.interfaces

import david.com.moviebrowser.model.ResponseBody
import david.com.moviebrowser.util.Constants.Companion.API_VERSION_3
import david.com.moviebrowser.util.Constants.Companion.movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET(API_VERSION_3 + movie + "top_rated")
    fun getTopRatedMovies(
            @Query("page") page: Int,
            @Query("api_key") apiKey: String,
            @Query("language") language: String?
    ): Call<ResponseBody>

}