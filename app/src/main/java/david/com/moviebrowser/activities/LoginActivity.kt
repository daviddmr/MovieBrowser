package david.com.moviebrowser.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import david.com.moviebrowser.R
import david.com.moviebrowser.adapters.ContentViewPagerAdapter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val contentViewPagerAdapter: ContentViewPagerAdapter = ContentViewPagerAdapter(supportFragmentManager, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initComponents()
    }

    private fun initComponents() {
        vpLoginContent.adapter = contentViewPagerAdapter
        tlLoginOptions.setupWithViewPager(vpLoginContent)
        updateTabs()
    }

    private fun updateTabs() {
        for (i in 0..tlLoginOptions.tabCount) {
            val tab = tlLoginOptions.getTabAt(i)
            tab?.customView = contentViewPagerAdapter.getTabView(i)
        }
    }
}
