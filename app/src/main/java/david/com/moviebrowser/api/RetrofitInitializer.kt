package david.com.moviebrowser.api

import david.com.moviebrowser.api.interfaces.MovieService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://yts.am/api/v2//")
            .build()

    fun githubService(): MovieService = retrofit.create(MovieService::class.java)

}