package david.com.moviebrowser.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import david.com.moviebrowser.R
import david.com.moviebrowser.fragments.LoginFragment
import david.com.moviebrowser.fragments.SignupFragment
import kotlinx.android.synthetic.main.custom_tab.view.*

class ContentViewPagerAdapter(fm: FragmentManager,
                              val context: Context) : FragmentStatePagerAdapter(fm) {

    private val PAGE_COUNT = 2
    private val tabTitles = arrayOf("Login", "Sign up")

    private val loginFragment = LoginFragment.newInstance()
    private val signupFragment = SignupFragment.newInstance()

    override fun getItem(position: Int): Fragment = if (position == 0) loginFragment else signupFragment

    override fun getCount(): Int = PAGE_COUNT

    override fun getPageTitle(position: Int): CharSequence = tabTitles[position]

    fun getTabView(position: Int): View {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_tab, null)
        view.tvTitleTab.text = tabTitles[position]
        return view
    }
}