package com.example.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//presentation layer must`t know anything about data layer
//
import com.example.shoppinglist.data.ShopListRepositoryImpl
//
// should be Dependency injection!!!

import com.example.shoppinglist.domain.EditItemUseCase
import com.example.shoppinglist.domain.GetShopListUseCase
import com.example.shoppinglist.domain.RemoveItemUseCase
import com.example.shoppinglist.domain.ShopItem

class MainViewModel : ViewModel() {

    //presentation layer must`t know anything about data layer
    // should be Dependency injection!!!
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeShopListUseCase = RemoveItemUseCase(repository)
    private val editShopListUseCase = EditItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList(){
        val list = getShopListUseCase.get()
        shopList.value = list
    }

    fun editShopItem(item: ShopItem){
        editShopListUseCase.edit(item)
        getShopList()
    }

    fun removeShopItem(item: ShopItem){
        removeShopListUseCase.remove(item)
        getShopList()
    }

    fun changeState(item: ShopItem){
        val newItem = item.copy(enabled = !item.enabled)
        editShopListUseCase.edit(newItem)
        getShopList()
    }


}