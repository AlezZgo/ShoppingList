package com.example.shoppinglist.domain

class GetItemByIdUseCase(private val shopListRepository: ShopListRepository) {
    fun get(id : Int) : ShopItem{
        return shopListRepository.getItem(id = id)
    }
}