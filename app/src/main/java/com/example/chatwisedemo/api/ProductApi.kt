package com.example.chatwisedemo.api

import com.example.chatwisedemo.model.ProductList
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ProductApi {
    @GET(value = "products")
    suspend fun getProducts():ProductList
}