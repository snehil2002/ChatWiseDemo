package com.example.chatwisedemo.dataorexception

sealed class Response<T>(val data:T?=null, val message:String?=null){
    class Success<T>(data:T?=null):Response<T>(data)
    class Loading<T>(data:T?=null):Response<T>(data)
    class Error<T>(data:T?=null,message: String?=null ):Response<T>(data, message)
}