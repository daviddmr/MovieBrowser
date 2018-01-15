package david.com.moviebrowser.api.interceptors

import david.com.moviebrowser.model.AccessToken
import david.com.moviebrowser.util.Constants.Companion.APPLICATION_JSON
import david.com.moviebrowser.util.Constants.Companion.HEADER_ACCEPT
import david.com.moviebrowser.util.Constants.Companion.HEADER_AUTHORIZATION
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class DefaultRequestInterceptor(val accessToken: AccessToken) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()

        val requestBuilder: Request.Builder = original.newBuilder()
                .header(HEADER_ACCEPT, APPLICATION_JSON)
                .header(HEADER_AUTHORIZATION, "${accessToken.tokenType} ${accessToken.accessToken}")
                .method(original.method(), original.body())

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }

}