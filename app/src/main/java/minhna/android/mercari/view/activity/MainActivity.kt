package minhna.android.mercari.view.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager;

import kotlinx.android.synthetic.main.activity_main.*
import minhna.android.mercari.R
import minhna.android.mercari.view.util.getAppCompatDrawable
import android.support.v4.app.FragmentStatePagerAdapter
import minhna.android.mercari.view.fragment.MainActivityFragment
import android.support.v4.view.ViewPager

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(this.getAppCompatDrawable(R.drawable.icon_launcher))
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setUpTabs()
        setUpViewPager()
    }

    private fun setUpTabs() {
        tabs.addTab(tabs.newTab().setText(getString(R.string.label_men)))
        tabs.addTab(tabs.newTab().setText(getString(R.string.label_all)))
        tabs.addTab(tabs.newTab().setText(getString(R.string.label_women)))
        tabs.getTabAt(1)?.select()
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.setCurrentItem(tab.position, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun setUpViewPager() {
        pager.setAdapter(TabsPagerAdapter(getSupportFragmentManager()))
        pager.setOffscreenPageLimit(2)
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                tabs.getTabAt(position)?.select()
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        pager.setCurrentItem(tabs.getSelectedTabPosition())
    }


    inner class TabsPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        val tabCount = 3
        override fun getItem(index: Int): Fragment? {
            when (index) {
                0 -> return MainActivityFragment.newInstance(0)
                1 -> return MainActivityFragment.newInstance(1)
                2 -> return MainActivityFragment.newInstance(2)
            }
            return null
        }

        override fun getCount(): Int {
            return tabCount
        }
    }
}
