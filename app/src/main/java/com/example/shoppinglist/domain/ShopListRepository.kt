package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun addItem(shopItem: ShopItem)

    fun editItem(shopItem: ShopItem)

    fun getItem(id: Int) : ShopItem

    fun getItemsList(): LiveData<List<ShopItem>>

    fun removeItem(shopItem: ShopItem)
}