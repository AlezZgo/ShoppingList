package com.example.shoppinglist.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R

class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val name = view.findViewById<TextView>(R.id.item_name)
    val count = view.findViewById<TextView>(R.id.item_count)
}