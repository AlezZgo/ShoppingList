package com.example.shoppinglist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem
import java.lang.RuntimeException

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    companion object{
        enum class EnabledType(val code: Int) {
            ENABLED(0),
            DISABLED(1)
        }

        const val MAX_ENABLED_POOL = 30
        const val MAX_DISABLED_POOL = 30
    }


    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        Log.i("created", "$viewType created")

        return ShopItemViewHolder(
            when(viewType){
                EnabledType.ENABLED.code -> LayoutInflater.from(parent.context).inflate(R.layout.item_shop_enabled,parent,false)
                EnabledType.DISABLED.code -> LayoutInflater.from(parent.context).inflate(R.layout.item_shop_disabled,parent,false)
                else -> throw RuntimeException("Unknown type of viewType")
            }
        )
    }


    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val elem = shopList[position]
        holder.count.text = elem.count.toString()

        holder.view.setOnLongClickListener {
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        val elem = shopList[position]

        val type = if (elem.enabled) {
            EnabledType.ENABLED
        } else {
            EnabledType.DISABLED
        }

        return type.code
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.item_name)
        val count = view.findViewById<TextView>(R.id.item_count)
    }


}
