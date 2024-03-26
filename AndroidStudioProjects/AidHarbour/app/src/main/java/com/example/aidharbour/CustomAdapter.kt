package com.example.recyclerviewmine2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.aidharbour.ItemsViewModel
import com.example.aidharbour.R

class CustomAdapter(private val mList: List<ItemsViewModel>, private val context: Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

   // private var onClickListener: OnClickListener? = null
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.textView2.text = ItemsViewModel.rating.toString()

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.name

        holder.itemView.setOnClickListener {
            Toast.makeText(context , "Volunteer "+ ItemsViewModel.name + " rated "+ItemsViewModel.rating.toString() + " stars", Toast.LENGTH_SHORT).show()
        }
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView2: TextView = itemView.findViewById(R.id.textView2)
        val textView: TextView = itemView.findViewById(R.id.textView)

    }
}