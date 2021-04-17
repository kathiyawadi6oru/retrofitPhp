package com.example.retrifitphp.ApiInterface


import com.example.retrifitphp.ResponseModel.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("registration.php")
    fun register(
        @Field("username") username:String,
        @Field("email") email:String,
        @Field("password") password:String
    ):Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("email") email:String,
        @Field("password") password:String
    ):Call<LoginResponse>

    @GET("fetchstudent.php")
    fun fatchAllStudent():Call<FatchAllStudentResponse>

    @FormUrlEncoded
    @POST("update.php")
    fun update(
            @Field("id") id:Int,
            @Field("username") username:String,
            @Field("email") email:String
    ):Call<UpdateResponse>

    @FormUrlEncoded
    @POST("delete.php")
    fun delete(
            @Field("id") id:Int
    ):Call<DeleteResponse>

}