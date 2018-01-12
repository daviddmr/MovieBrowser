package david.com.moviebrowser.api

import david.com.moviebrowser.api.interfaces.GenreService
import david.com.moviebrowser.api.interfaces.MovieService
import david.com.moviebrowser.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private var logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
    private var httpClient: OkHttpClient.Builder

    init {
        logging.level = HttpLoggingInterceptor.Level.BASIC

        httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
    }

    private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .build()

    fun movieService(): MovieService = retrofit.create(MovieService::class.java)
    fun genreService(): GenreService = retrofit.create(GenreService::class.java)

}