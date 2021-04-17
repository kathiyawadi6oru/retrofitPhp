package com.example.retrifitphp.ResponseModel

import com.example.retrifitphp.ModelClass.User
import com.google.gson.annotations.SerializedName

class FatchAllStudentResponse (
        @SerializedName("user")
    var studentList:ArrayList<User>,
    var error:Int
)
