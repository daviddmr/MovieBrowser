package david.com.moviebrowser.model

class AccessToken {

    val accessToken: String = ""

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

    val expiresIn: Int = 0

    val refreshToken: String = ""

    val scope: String = ""

}