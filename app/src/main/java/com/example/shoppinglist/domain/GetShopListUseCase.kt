package com.example.shoppinglist.domain

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun get() : List<ShopItem>{
        return shopListRepository.getItemsList()
    }

}