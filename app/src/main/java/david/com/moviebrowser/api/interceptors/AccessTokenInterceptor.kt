package david.com.moviebrowser.api.interceptors

import android.util.Base64
import david.com.moviebrowser.util.Constants.Companion.APPLICATION_JSON
import david.com.moviebrowser.util.Constants.Companion.FORM_URL_ENCODED
import david.com.moviebrowser.util.Constants.Companion.HEADER_ACCEPT
import david.com.moviebrowser.util.Constants.Companion.HEADER_AUTHORIZATION
import david.com.moviebrowser.util.Constants.Companion.HEADER_CONTENT_TYPE
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AccessTokenInterceptor(
        private val clientId: String,
        private val clientSecret: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()

        val data = "$clientId:$clientSecret"
        val base64 = Base64.encodeToString(data.toByteArray(), Base64.NO_WRAP)

        val requestBuilder: Request.Builder = original.newBuilder()
                .header(HEADER_CONTENT_TYPE, FORM_URL_ENCODED)
                .header(HEADER_ACCEPT, APPLICATION_JSON)
                .header(HEADER_AUTHORIZATION, "Basic $base64")
                .method(original.method(), original.body())

        val request: Request = requestBuilder.build()

        return chain.proceed(request)
    }

}
