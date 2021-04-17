package com.example.retrifitphp.ResponseModel

import com.example.retrifitphp.ModelClass.User


class LoginResponse (
    var user: User,
    var error: Int,
    var message: String
)