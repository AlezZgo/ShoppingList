package com.example.shoppinglist.domain

class RemoveItemUseCase(private val shopListRepository: ShopListRepository) {
    fun remove(item: ShopItem){
        return shopListRepository.removeItem(shopItem = item)
    }
}