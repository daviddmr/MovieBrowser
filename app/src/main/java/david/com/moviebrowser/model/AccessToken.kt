package david.com.moviebrowser.model

import com.google.gson.annotations.SerializedName

class AccessToken {

    @SerializedName("access_token")
    val accessToken: String = ""

    @SerializedName("token_type")
    var tokenType: String = ""
        get() {
            // OAuth requires uppercase Authorization HTTP header value for token type
            if (field.isNotBlank() && !Character.isUpperCase(field[0])) {
                field = Character
                        .toString(field[0])
                        .toUpperCase() + field.substring(1)
            }

            return field
        }

    @SerializedName("expires_in")
    val expiresIn: Int = 0

    @SerializedName("refresh_token")
    val refreshToken: String = ""

    val scope: String = ""

}