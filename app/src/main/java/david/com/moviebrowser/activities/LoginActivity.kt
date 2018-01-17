package david.com.moviebrowser.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import david.com.moviebrowser.R
import david.com.moviebrowser.adapters.ContentViewPagerAdapter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

}
