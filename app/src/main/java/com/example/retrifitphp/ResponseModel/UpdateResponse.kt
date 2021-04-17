package com.example.retrifitphp.ResponseModel


import com.example.retrifitphp.ModelClass.User
import com.google.gson.annotations.SerializedName

class UpdateResponse(
        var user: User,
        var error:Int,
        @SerializedName("message")
        var msg:String
)