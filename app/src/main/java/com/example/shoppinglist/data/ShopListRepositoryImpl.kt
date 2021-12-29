package com.example.shoppinglist.data

import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

object ShopListRepositoryImpl : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrement = 0

    init {
        for (i in 0 .. 10){
            val item = ShopItem("name $i", i ,  true)
            addItem(item)
        }

    }

    override fun addItem(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.UNDEFINED_ID){
            autoIncrement++
        }
        shopList.add(shopItem)
    }

    override fun editItem(newItem: ShopItem) {
        val oldItem = getItem(newItem.id)
        shopList.remove(oldItem)
        addItem(newItem)
    }

    override fun getItem(id: Int): ShopItem {
        return shopList.find {
            it.id == id
        } ?: throw  RuntimeException("Element with id $id not found")
    }

    override fun getItemsList(): List<ShopItem> {
        return shopList.toList()
    }

    override fun removeItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }
}