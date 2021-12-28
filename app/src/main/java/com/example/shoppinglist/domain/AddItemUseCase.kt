package com.example.shoppinglist.domain

class AddItemUseCase(private val shopListRepository: ShopListRepository) {
    fun add(item : ShopItem){
        shopListRepository.addItem(shopItem = item)
    }
}