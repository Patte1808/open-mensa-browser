package com.nevereatalone.feature.onboarding

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ProfilePictureAdapter constructor(
        val context: Context,
        val profilePictures: List<String>
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val profilePicture = CircleImageView(context)
        val url = getItem(position)

        Picasso.get().load(url).into(profilePicture)

        profilePicture.layoutParams = ViewGroup.LayoutParams(48, 48)
        profilePicture.alpha = 0.3f

        profilePicture.setOnClickListener {
            profilePicture.alpha = 1.0f
        }

        return profilePicture
    }

    override fun getItem(position: Int): String = profilePictures[position]

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int = profilePictures.count()
}