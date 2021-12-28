package com.example.shoppinglist.domain

class EditItemUseCase(private val shopListRepository: ShopListRepository) {
    fun edit(item : ShopItem){
        shopListRepository.editItem(shopItem = item)
    }
}