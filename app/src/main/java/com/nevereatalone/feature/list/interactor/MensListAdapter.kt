package com.nevereatalone.feature.list.interactor

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nevereatalone.R
import com.nevereatalone.feature.models.Canteen
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.mensa_list_item.*


class MensListAdapter @JvmOverloads constructor(val canteens: List<Canteen>) : RecyclerView.Adapter<MensListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.mensa_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(canteens[position])

    override fun getItemCount(): Int = canteens.size


    class ViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(item: Canteen) {
            with(item) {
                tv_canteen_name.text = item.name
                tv_canteen_address.text = item.address
            }
        }

    }
}