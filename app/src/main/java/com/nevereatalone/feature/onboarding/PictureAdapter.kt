package com.nevereatalone.feature.onboarding

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class PictureAdapter constructor(
        val context: Context,
        val pictures: List<String>,
        val state: OnboardingState,
        val screen: Screen
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val pictureImageView = CircleImageView(context)
        val url = getItem(position)

        Picasso.get().load(url).into(pictureImageView)

        pictureImageView.layoutParams = ViewGroup.LayoutParams(56, 56)
        pictureImageView.alpha = 0.3f

        pictureImageView.setOnClickListener {
            val isCurrentlySelected = it.alpha == 1.0f

            it.alpha = if (isCurrentlySelected) 0.5f else 1.0f

            when (screen) {
                ProfileScreen -> {
                    parent?.let {
                        for (counter in 0..it.childCount) {
                            if (counter != position) {
                                val image = it.getChildAt(counter) as? ImageView

                                image?.alpha = 0.5f
                            }
                        }

                        state.profilePicture = url
                    }
                }
                TasteScreen -> {
                    if (isCurrentlySelected)
                        state.tastePictures.remove(url)
                    else
                        state.tastePictures.add(url)
                }
            }
        }

        return pictureImageView
    }

    override fun getItem(position: Int): String = pictures[position]

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int = pictures.count()
}