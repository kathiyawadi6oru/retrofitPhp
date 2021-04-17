package com.example.retrifitphp.Activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.retrifitphp.R
import com.example.retrifitphp.ResponseModel.LoginResponse
import com.example.retrifitphp.Retrofit.RetrofitClient
import com.example.retrifitphp.SharedPreference.SharedPreferenceManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var mEdtUserLoginEmail: EditText
    lateinit var mEdtUserLoginPassword: EditText
    lateinit var mBtnLogin: Button
    lateinit var mTxtGoToRegister: TextView
    lateinit var sharedPreferenceManager: SharedPreferenceManager

    companion object{
        val KEY = "LoginActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //hide action bar
        supportActionBar!!.hide()

        //hide status bar
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        mTxtGoToRegister  =findViewById(R.id.txtGoToRegisterPage)
        mEdtUserLoginEmail  =findViewById(R.id.edtUserLoginEmail)
        mEdtUserLoginPassword  =findViewById(R.id.edtUserLoginPassword)
        mBtnLogin = findViewById(R.id.btnLogin)
        sharedPreferenceManager = SharedPreferenceManager(this)


        //Redirect user to Register Page
        mTxtGoToRegister.setOnClickListener {
            var intent = Intent(this, RegistrationActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        mBtnLogin.setOnClickListener {
            studLogin()
        }
    }
    private fun studLogin() {

        val userEmail = mEdtUserLoginEmail.text.toString()
        val userPassword = mEdtUserLoginPassword.text.toString()


        if (userEmail.isEmpty()){
            mEdtUserLoginEmail.requestFocus()
            mEdtUserLoginEmail.error = "Please Enter Email"
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            mEdtUserLoginEmail.requestFocus()
            mEdtUserLoginEmail.error = "Please Enter Correct Email"
            return
        }
        if (userPassword.isEmpty()){
            mEdtUserLoginPassword.requestFocus()
            mEdtUserLoginPassword.error = "Please Enter Email"
            return
        }
        if (userPassword.length<8){
            mEdtUserLoginPassword.requestFocus()
            mEdtUserLoginPassword.error = "Password must have 8 Length"
            return
        }

        var call:Call<LoginResponse> = RetrofitClient()
            .getInstance()
            .getApi()
            .login(userEmail,userPassword)

        call.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val loginResponse:LoginResponse = response.body()!!

                if (response.isSuccessful){
                    if (loginResponse.error == 1){
                        Toast.makeText(this@LoginActivity,loginResponse.message, Toast.LENGTH_LONG).show()
                        Log.d(KEY,loginResponse.message)
                    }else{
                        sharedPreferenceManager.saveUser(loginResponse.user)
                        var intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        Toast.makeText(this@LoginActivity,loginResponse.message, Toast.LENGTH_LONG).show()
                        Log.d(KEY,loginResponse.message)
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity,t.message.toString(), Toast.LENGTH_LONG).show()
                Log.d(KEY,t.message.toString())
            }

        })
    }

    override fun onStart() {
        super.onStart()
        if (sharedPreferenceManager.isLoggedIn()){
            var intent = Intent(this@LoginActivity, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}