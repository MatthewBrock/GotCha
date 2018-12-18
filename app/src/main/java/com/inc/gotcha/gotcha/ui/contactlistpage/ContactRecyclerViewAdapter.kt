package com.inc.gotcha.gotcha.ui.contactlistpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inc.gotcha.gotcha.ProfileData
import com.inc.gotcha.gotcha.R


class ContactRecyclerViewAdapter(private val profileList: List<ProfileData>, private val adapterCallbacks: AdapterCallbacks) :
        RecyclerView.Adapter<ContactRecyclerViewAdapter.ContactListViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ContactListViewHolder(val view: View, adapterCallbacks: AdapterCallbacks, profileList: List<ProfileData>) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val subTitle: TextView = view.findViewById(R.id.subTitle)
        val likeImage: ImageView = view.findViewById(R.id.like_image)
        val touchArea: View = view.findViewById(R.id.touchable_area)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.contact_cell, parent, false)

        return ContactListViewHolder(itemView, adapterCallbacks, profileList)
    }

    // Replace the contents of a view (invoked by the layout manager)

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        val profileData = profileList[position]
        holder.title?.text = profileData.name
        holder.likeImage.setOnClickListener { holder.likeImage.setImageResource(R.drawable.btn_fav_red) }
        holder.touchArea.setOnClickListener { adapterCallbacks.openContactPage(profileList[position]) }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = profileList.size
}