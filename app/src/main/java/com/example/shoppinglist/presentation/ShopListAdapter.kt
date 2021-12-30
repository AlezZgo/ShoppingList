package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    companion object{
        enum class ShopItemViewType(val code: Int) {
            ENABLED_VIEW(0),
            DISABLED_VIEW(1)
        }

        const val MAX_ENABLED_POOL = 30
        const val MAX_DISABLED_POOL = 30
    }

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onShopItemLongClickListener : ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        return ShopItemViewHolder(
            when(viewType){
                ShopItemViewType.ENABLED_VIEW.code -> LayoutInflater.from(parent.context).inflate(R.layout.item_shop_enabled,parent,false)
                ShopItemViewType.DISABLED_VIEW.code -> LayoutInflater.from(parent.context).inflate(R.layout.item_shop_disabled,parent,false)
                else -> throw RuntimeException("Unknown type of viewType")
            }
        )
    }


    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val elem = shopList[position]
        holder.count.text = elem.count.toString()

        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(elem)
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        val elem = shopList[position]

        val type = if (elem.enabled) {
            ShopItemViewType.ENABLED_VIEW
        } else {
            ShopItemViewType.DISABLED_VIEW
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
