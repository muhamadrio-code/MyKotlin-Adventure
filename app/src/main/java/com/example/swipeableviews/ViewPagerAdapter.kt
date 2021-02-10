package com.example.swipeableviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.item_view_pager.view.*


class ViewPagerAdapter(
    val images : List<Int>
) : RecyclerView.Adapter<ViewPagerAdapter.viewPagerViewHolder>() {
    inner class viewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager, parent, false)
        return viewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
       return images.size
    }

    override fun onBindViewHolder(holder: viewPagerViewHolder, position: Int) {
        val image = images[position]
        holder.itemView.ivImage.setImageResource(image)
    }
}