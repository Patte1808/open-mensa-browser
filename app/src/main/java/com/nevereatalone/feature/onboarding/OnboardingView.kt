package com.nevereatalone.feature.onboarding

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nevereatalone.R
import kotlinx.android.synthetic.main.activity_onboarding.*


class OnboardingView : AppCompatActivity() {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        container.apply {
            adapter = mSectionsPagerAdapter
        }

        container.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
            }
        })

    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return PlaceholderFragment.newInstance(position, ContextCompat.getColor(applicationContext, R.color.materialWhite))
        }

        override fun getCount(): Int {
            return 3
        }
    }

    class PlaceholderFragment : Fragment() {

        var backgroundColor: Int = 0
        var pageNumber: Int = 0

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            backgroundColor = arguments.getInt(BACKGROUND_COLOR)
            pageNumber = arguments.getInt(PAGE_NUMBER)
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            var layout: Int

            when(pageNumber) {
                1 -> layout = R.layout.fragment_onboarding_profile
                2 -> layout = R.layout.fragment_onboarding_taste
                else -> layout = R.layout.fragment_onboarding_introduction
            }

            return inflater.inflate(layout, container, false)
        }

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            view?.findViewById<View>(R.id.background)?.setBackgroundColor(backgroundColor)
        }

        companion object {

            const val BACKGROUND_COLOR = "backgroundColor"
            const val PAGE_NUMBER = "pageNumber"

            fun newInstance(pageNumber: Int, backgroundColor: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(PAGE_NUMBER, pageNumber)
                args.putInt(BACKGROUND_COLOR, backgroundColor)
                fragment.arguments = args
                return fragment
            }
        }
    }
}
