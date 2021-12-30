package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemCallBack()) {

    companion object {
        enum class ShopItemViewType(val code: Int) {
            ENABLED_VIEW(0),
            DISABLED_VIEW(1)
        }

        const val MAX_ENABLED_POOL = 30
        const val MAX_DISABLED_POOL = 30
    }

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        return ShopItemViewHolder(
            when (viewType) {
                ShopItemViewType.ENABLED_VIEW.code -> LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_shop_enabled, parent, false)
                ShopItemViewType.DISABLED_VIEW.code -> LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_shop_disabled, parent, false)
                else -> throw RuntimeException("Unknown type of viewType")
            }
        )
    }


    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val elem = getItem(position)
        holder.count.text = elem.count.toString()
        holder.name.text = elem.name

        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(elem)
            true
        }
        holder.view.setOnClickListener {
            onShopItemClickListener?.invoke(elem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val elem = getItem(position)

        val type = if (elem.enabled) {
            ShopItemViewType.ENABLED_VIEW
        } else {
            ShopItemViewType.DISABLED_VIEW
        }

        return type.code
    }

}
