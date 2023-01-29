package com.rzs.corroutinesproject.presentation.recyclerview.retrofitrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rzs.corroutinesproject.R
import com.rzs.corroutinesproject.domain.model.Country

class RetrofitViewAdapter(private val mList: MutableList<Country>)
    : RecyclerView.Adapter<RetrofitViewAdapter.ViewHolder>() {
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.network_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // sets the image to the imageview from our itemHolder class
        Glide.with(holder.itemView).load(mList[position].flag).into(holder.flag)

        // sets the text to the textview from our itemHolder class
        holder.country.text = mList[position].countryName
        holder.capital.text = mList[position].capital

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val flag: ImageView = itemView.findViewById(R.id.imageview)
        val country: TextView = itemView.findViewById(R.id.textView1)
        val capital: TextView = itemView.findViewById(R.id.textView2)
    }

    fun submitList(newData: List<Country>) {
        mList.clear()
        mList.addAll(newData)
        notifyDataSetChanged()
    }
}