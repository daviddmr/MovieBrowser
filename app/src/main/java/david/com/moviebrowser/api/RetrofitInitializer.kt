package david.com.moviebrowser.api

import david.com.moviebrowser.api.interfaces.MovieService
import david.com.moviebrowser.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    fun movieService(): MovieService = retrofit.create(MovieService::class.java)

}