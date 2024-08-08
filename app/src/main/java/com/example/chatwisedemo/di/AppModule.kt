package com.example.chatwisedemo.di

import com.example.chatwisedemo.api.Constants
import com.example.chatwisedemo.api.ProductApi
import com.example.chatwisedemo.repository.ProductRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getApi():ProductApi{
        return Retrofit.Builder().baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ProductApi::class.java)
    }

    @Singleton
    @Provides
    fun provideProductRepo(productApi: ProductApi):ProductRepo{
        return ProductRepo(productApi)

    }
}