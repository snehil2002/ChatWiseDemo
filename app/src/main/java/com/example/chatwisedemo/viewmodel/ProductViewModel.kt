package com.example.chatwisedemo.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatwisedemo.dataorexception.Response
import com.example.chatwisedemo.model.Product
import com.example.chatwisedemo.repository.ProductRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepo: ProductRepo) :ViewModel(){
    private var _isLoading=MutableStateFlow(true)
    var isLoading:StateFlow<Boolean> = _isLoading

    private var _allProducts=MutableStateFlow<List<Product>>(emptyList())
    var allProducts:StateFlow<List<Product>> = _allProducts

    init {
        getAllProducts()
    }

    fun getAllProducts(){
        viewModelScope.launch (Dispatchers.Default){
            when(val response=productRepo.getAllProducts()){
                is Response.Success -> {
                    _allProducts.value= response.data!!
                    if (!response.data.isNullOrEmpty())
                        _isLoading.value=false
                }
                is Response.Error -> {
                    _isLoading.value=false
                    Log.d("EEEE","1111")
                }
                else -> {
                    _isLoading.value=false
                    Log.d("EEEE","1111")
                }
            }
        }
    }
}