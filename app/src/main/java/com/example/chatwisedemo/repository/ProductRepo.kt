package com.example.chatwisedemo.repository

import com.example.chatwisedemo.api.ProductApi
import com.example.chatwisedemo.dataorexception.Response
import com.example.chatwisedemo.model.Product
import javax.inject.Inject

class ProductRepo @Inject constructor(private val productApi: ProductApi) {
    suspend fun getAllProducts():Response<List<Product>>{
        return try {
            Response.Loading(true)
            val allProducts=productApi.getProducts().products
            if (allProducts.isNotEmpty())
                Response.Loading(false)
            Response.Success(data = allProducts)

        }catch (e:Exception){
            Response.Error(message = e.message.toString())

        }
    }
}