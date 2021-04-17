package com.example.retrifitphp.ResponseModel


import com.google.gson.annotations.SerializedName

class RegisterResponse(
        var error:Int,
        @SerializedName("message")
        var msg:String
)