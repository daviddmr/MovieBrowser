package david.com.moviebrowser.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.view.View
import david.com.moviebrowser.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity(), Runnable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val handler = Handler()
        handler.postDelayed(this, 3000)
    }

    override fun run() {
        startMainActivity()
    }

    private fun startMainActivity() {
        val intent = Intent(applicationContext, LoginActivity::class.java)

        val pair = Pair(ivIcon as View, "splashToLoginImage")
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pair)

        startActivity(intent, options.toBundle())
        finish()
    }

}
