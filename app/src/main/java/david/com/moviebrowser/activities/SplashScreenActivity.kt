package david.com.moviebrowser.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import david.com.moviebrowser.BaseActivity
import david.com.moviebrowser.R

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
        val intent = Intent(applicationContext, BaseActivity::class.java)
        startActivity(intent)
        finish()
    }

}
