package david.com.moviebrowser

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import david.com.moviebrowser.fragments.MovieFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_movies -> {
                commitMovieFragment()
            }
            R.id.nav_movies_by_genre -> {

            }
            R.id.nav_favorites -> {

            }
            R.id.nav_top10 -> {

            }
            R.id.nav_register -> {

            }
            R.id.nav_logout -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun commitMovieFragment() {
        val fragment: Fragment = MovieFragment.newInstance()
        commitFragment(fragment, MovieFragment.TAG)
    }

    private fun commitFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_content, fragment, tag)
                .addToBackStack(tag)
                .commitAllowingStateLoss()
    }

}
