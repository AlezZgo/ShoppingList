package com.example.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.AddItemUseCase
import com.example.shoppinglist.domain.EditItemUseCase
import com.example.shoppinglist.domain.GetItemByIdUseCase
import com.example.shoppinglist.domain.ShopItem

class ShopItemViewModel : ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopItemUseCase = GetItemByIdUseCase(repository)
    private val addShopItemUseCase = AddItemUseCase(repository)
    private val editShopItemUseCase = EditItemUseCase(repository)

    fun getItem(itemId : Int){
        val item = getShopItemUseCase.get(itemId)
    }

    fun addItem(inputName : String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldValid = validateInput(name,count)
        if(fieldValid){
            val shopItem = ShopItem(name,count,true)
            addShopItemUseCase.add(shopItem)
        }

    }

    fun editItem(inputName : String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldValid = validateInput(name,count)
        if(fieldValid){
            val shopItem = ShopItem(name,count,true)
            editShopItemUseCase.edit(shopItem)
        }
    }

    private fun parseName(inputName : String?) : String{
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?) : Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        }catch (e : Exception){
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean{
        var result = true
        if(name.isBlank()){
            //TODO: show error input name
            result = false
        }
        if (count<=0){
            //TODO: show error input count
            result = false
        }
        return result
    }

}