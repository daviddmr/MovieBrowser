package david.com.moviebrowser.application

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MovieBrowserApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val realmConfig = RealmConfiguration
                .Builder()
                .name("movie_browser.realm")
                .schemaVersion(1)
                .build()

        Realm.setDefaultConfiguration(realmConfig)

    }
}