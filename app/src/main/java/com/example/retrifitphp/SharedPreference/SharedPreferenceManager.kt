package com.example.retrifitphp.SharedPreference

import android.content.Context
import android.content.SharedPreferences
import com.example.retrifitphp.ModelClass.User

class SharedPreferenceManager(var context:Context) {
    val SHARED_PREF_NAME = "RetrofitPhp"
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    fun saveUser(user: User){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.putString("id", user.Stud_id)
        editor.putString("name",user.Stud_name)
        editor.putString("email",user.Email)
        editor.putBoolean("logged",true)
        editor.apply()

    }

    fun isLoggedIn():Boolean{
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("logged",false)

    }

    fun get_user(): User {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)

       return User(

               sharedPreferences.getString("id",null).toString(),
               sharedPreferences.getString("name",null).toString(),
               sharedPreferences.getString("email",null).toString()

       )
    }
    fun sign_out(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().commit()

    }

}