package com.nevereatalone.feature.onboarding

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.TextView
import com.nevereatalone.App
import com.nevereatalone.R
import com.nevereatalone.navi.bottom_navi.AppContainer
import kotlinx.android.synthetic.main.activity_onboarding.*
import javax.inject.Inject


class OnboardingView : AppCompatActivity(), OnboardingContract.View {

    val Activity.app: App
        get() = application as App


    @Inject
    lateinit var presenter: OnboardingContract.Presenter

    @Inject
    lateinit var state: OnboardingState

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

        container.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {

            }
        })


        finish_onboarding.setOnClickListener {
            presenter.onFinishOnboarding(state)
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

    override fun openCanteenList() {
        val intent = Intent(this, AppContainer::class.java)

        startActivity(intent)
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return PlaceholderFragment.newInstance(position, ContextCompat.getColor(applicationContext, R.color.materialWhite), state)
        }

        override fun getCount(): Int {
            return 3
        }
    }

    class PlaceholderFragment : Fragment() {

        var backgroundColor: Int = 0
        var pageNumber: Int = 0
        var state: OnboardingState = OnboardingState()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            backgroundColor = arguments.getInt(BACKGROUND_COLOR)
            pageNumber = arguments.getInt(PAGE_NUMBER)
            state = arguments.getParcelable(STATE)
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
                adapter = PictureAdapter(context, listOf("https://placeimg.com/640/480/any", "https://placeimg.com/640/480/any",
                        "https://placeimg.com/640/480/any", "https://placeimg.com/640/480/any",
                        "https://placeimg.com/640/480/any", "https://placeimg.com/640/480/any"), state, ProfileScreen)
            }

            view?.findViewById<GridView>(R.id.taste_picture_layout)?.apply {
                adapter = PictureAdapter(context, listOf("https://placeimg.com/640/480/any", "https://placeimg.com/640/480/any",
                        "https://placeimg.com/640/480/any", "https://placeimg.com/640/480/any",
                        "https://placeimg.com/640/480/any", "https://placeimg.com/640/480/any"), state, TasteScreen)
            }

            view?.findViewById<TextView>(R.id.input_username)?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    state.username = s.toString()
                }
            })

            view?.findViewById<View>(R.id.background)?.setBackgroundColor(backgroundColor)
        }

        companion object {
            const val BACKGROUND_COLOR = "backgroundColor"
            const val PAGE_NUMBER = "pageNumber"
            const val STATE = "state"

            fun newInstance(pageNumber: Int, backgroundColor: Int, state: OnboardingState): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(PAGE_NUMBER, pageNumber)
                args.putInt(BACKGROUND_COLOR, backgroundColor)
                args.putParcelable(STATE, state)
                fragment.arguments = args
                return fragment
            }
        }
    }
}
