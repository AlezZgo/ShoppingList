package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun get() : LiveData<List<ShopItem>> {
        return shopListRepository.getItemsList()
    }

}