package com.example.retrifitphp.Retrofit


import com.example.retrifitphp.ApiInterface.Api
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class   RetrofitClient {

    val BASE_URL = "http://192.168.43.129/studentApi/"
    var retrofitClient:RetrofitClient? = null
    lateinit var retrofit:Retrofit


    val builder:OkHttpClient.Builder = OkHttpClient.Builder()
    val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()

    constructor(){

        val gson = GsonBuilder()
                .setLenient()
                .create()

        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)

        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build()
    }

    fun getInstance():RetrofitClient{
        if (retrofitClient == null){
            retrofitClient = RetrofitClient()
        }

        return retrofitClient as RetrofitClient
    }

    fun getApi():Api{
        return retrofit.create(Api::class.java)
    }
}