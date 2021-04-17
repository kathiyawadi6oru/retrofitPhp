package com.example.retrifitphp.ResponseModel

import com.google.gson.annotations.SerializedName

class DeleteResponse (
    var error:Int,
    @SerializedName("message")
    var msg:String
)
