package com.rzs.corroutinesproject.presentation.recyclerview.paginationrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rzs.corroutinesproject.R
import com.rzs.corroutinesproject.domain.model.Country
import com.rzs.corroutinesproject.domain.model.Game

class PaginationViewAdapter(private val mList: MutableList<Game>)
    : RecyclerView.Adapter<PaginationViewAdapter.ViewHolder>() {
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // sets the image to the imageview from our itemHolder class
        //Glide.with(holder.itemView).load(mList[position].flag).into(holder.flag)

        // sets the text to the textview from our itemHolder class
        holder.releaseDate.text = mList[position].url
        holder.name.text = mList[position].name
        holder.summary.text = mList[position].summary

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(GameView: View) : RecyclerView.ViewHolder(GameView) {
        val releaseDate: TextView = GameView.findViewById(R.id.release_date)
        val name: TextView = GameView.findViewById(R.id.game)
        val summary: TextView = GameView.findViewById(R.id.summary)
    }

    fun submitList(newData: List<Game>) {
        mList.clear()
        mList.addAll(newData)
        notifyDataSetChanged()
    }
}