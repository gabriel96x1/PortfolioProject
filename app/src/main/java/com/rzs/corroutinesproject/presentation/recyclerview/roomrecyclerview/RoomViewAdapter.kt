package com.rzs.corroutinesproject.presentation.recyclerview.roomrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rzs.corroutinesproject.R
import com.rzs.corroutinesproject.domain.model.User

class RoomViewAdapter(private val mList: MutableList<User>)
    : RecyclerView.Adapter<RoomViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.room_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // sets the image to the imageview from our itemHolder class

        // sets the text to the textview from our itemHolder class
        holder.name.text = mList[position].userName
        holder.uid.text = mList[position].uid.toString()
    }
    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val uid: TextView = itemView.findViewById(R.id.uid)
    }

    fun submitList(newData: List<User>) {
        mList.clear()
        mList.addAll(newData)
        notifyDataSetChanged()
    }

}