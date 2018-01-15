package david.com.moviebrowser.api

import david.com.moviebrowser.api.interceptors.AccessTokenInterceptor
import david.com.moviebrowser.api.interceptors.DefaultRequestInterceptor
import david.com.moviebrowser.model.AccessToken
import david.com.moviebrowser.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    var logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
    var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()

    init {
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
    }

    val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    inline fun <reified T> service(serviceClass: Class<T>, accessToken: AccessToken): T {
        httpClient.addInterceptor(DefaultRequestInterceptor(accessToken))
        return service(serviceClass)
    }

    inline fun <reified T> service(serviceClass: Class<T>, clientId: String, clientSecret: String): T {
        httpClient.addInterceptor(AccessTokenInterceptor(clientId, clientSecret))
        return service(serviceClass)
    }

    inline fun <reified T> service(serviceClass: Class<T>): T {
        val retrofit: Retrofit = builder.client(httpClient.build()).build()

        return retrofit.create(serviceClass)
    }

}