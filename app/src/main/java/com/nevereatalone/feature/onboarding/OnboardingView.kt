package com.nevereatalone.feature.onboarding

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import com.nevereatalone.App
import com.nevereatalone.R
import kotlinx.android.synthetic.main.activity_onboarding.*
import javax.inject.Inject


class OnboardingView : AppCompatActivity(), OnboardingContract.View {

    val Activity.app: App
        get() = application as App


    @Inject
    lateinit var presenter: OnboardingContract.Presenter

    val component by lazy { app.component.plus(OnboardingModule(this)) }

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        component.inject(this)
        presenter.onAttached()

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        container.apply {
            adapter = mSectionsPagerAdapter
        }

        val dots = mutableListOf<ImageView>()

        container.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                var i = 0

                Log.wtf("Test", (PlaceholderFragment) )

                while(i < container.adapter.count) {
                    dots.add(i, ImageView(applicationContext))

                    if(position == i)
                        dots[i].setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.non_selected_item_dot))
                    else
                        dots[i].setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.selected_item_dot))

                    i++
                }
            }
        })

        for(dot in dots) {
            viewPagerCountDots.addView(dot)
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.onShown()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetached()
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

            view?.findViewById<GridView>(R.id.profile_picture_layout)?.apply {
                adapter = ProfilePictureAdapter(context, listOf("https://placeimg.com/640/480/any", "https://placeimg.com/640/480/any",
                        "https://placeimg.com/640/480/any", "https://placeimg.com/640/480/any",
                        "https://placeimg.com/640/480/any", "https://placeimg.com/640/480/any"))
            }
            //view?.findViewById<View>(R.id.profile_image)?.alpha = 0.1f

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
