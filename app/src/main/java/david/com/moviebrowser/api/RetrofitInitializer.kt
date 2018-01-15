package david.com.moviebrowser.api

import david.com.moviebrowser.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private var logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
    var httpClient: OkHttpClient.Builder

    init {
        logging.level = HttpLoggingInterceptor.Level.BASIC

        httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
    }

    val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .build()

    inline fun <reified T> service(serviceClass: Class<T>): T = retrofit.create(serviceClass)

}