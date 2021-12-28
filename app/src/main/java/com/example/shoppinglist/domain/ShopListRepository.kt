package com.example.shoppinglist.domain

interface ShopListRepository {
    fun addItem(shopItem: ShopItem)

    fun editItem(shopItem: ShopItem)

    fun getItem(id: Int) : ShopItem

    fun getItemsList(): List<ShopItem>

    fun removeItem(shopItem: ShopItem)
}