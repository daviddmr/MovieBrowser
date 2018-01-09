package david.com.moviebrowser.api.interfaces

import david.com.moviebrowser.model.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("list_movies.json")
    fun getAllMovies(
            @Query("limit") limit: Int,
            @Query("page") page: Int,
            @Query("genre") genre: String?
    ): Call<ResponseBody>

}