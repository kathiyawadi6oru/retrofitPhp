package com.example.retrifitphp.Fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.retrifitphp.Activity.LoginActivity
import com.example.retrifitphp.Activity.RegistrationActivity
import com.example.retrifitphp.R
import com.example.retrifitphp.ResponseModel.DeleteResponse
import com.example.retrifitphp.ResponseModel.UpdateResponse
import com.example.retrifitphp.Retrofit.RetrofitClient
import com.example.retrifitphp.SharedPreference.SharedPreferenceManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {
    lateinit var EdtUserName: EditText
    lateinit var EdtUserEmail: EditText
    lateinit var BtnUpdate: Button
    lateinit var Edit: ImageView
    lateinit var tv: TextView
    lateinit var Delete : ImageView

    lateinit var sharedPrefrenceManager: SharedPreferenceManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val inflater: MenuInflater = MenuInflater(activity)
        inflater.inflate(R.menu.menubar, menu)

    }
    /*

    fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = MenuInflater(activity)
        inflater.inflate(R.menu.menubar, menu)
        return true
    }
*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.signout -> {
                Toast.makeText(activity, "Log Out", Toast.LENGTH_SHORT)
                        .show()
                sharedPrefrenceManager.sign_out()
                var intent = Intent(activity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            else -> {
            }
        }
        return true
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        EdtUserName = view.findViewById(R.id.updateedtUserName)
        EdtUserEmail = view.findViewById(R.id.updateedtUserEmail)
        BtnUpdate  = view.findViewById(R.id.btnUpdate)
        sharedPrefrenceManager = SharedPreferenceManager(activity!!)
        Edit = view.findViewById(R.id.editimg)
        tv = view.findViewById(R.id.updatetextView)
        Delete = view.findViewById(R.id.deleteimg)


        var studId:Int = sharedPrefrenceManager.get_user().Stud_id.toInt()

        EdtUserName.setText(sharedPrefrenceManager.get_user().Stud_name)
        EdtUserEmail.setText(sharedPrefrenceManager.get_user().Email)
        editoff()



        var builder: AlertDialog.Builder

        Delete.setOnClickListener {
            builder = AlertDialog.Builder(activity)
            builder.setTitle("Delete User!!!")
            builder.setMessage("Do you want to delete User ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id -> deleteuser(studId) })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
            val alert = builder.create()
            alert.show()
            
        }


        Edit.setOnClickListener {
            editon()
        }

        BtnUpdate.setOnClickListener {
            updateUser(studId)
            editoff()
        }

    }

    private fun editoff() {
        tv.visibility = View.INVISIBLE
        BtnUpdate.visibility = View.INVISIBLE
        Edit.visibility = View.VISIBLE
        EdtUserName.isEnabled = false
        EdtUserEmail.isEnabled = false
        Delete.visibility = View.VISIBLE
    }

    private fun editon() {
        tv.visibility = View.VISIBLE
        BtnUpdate.visibility = View.VISIBLE
        Edit.visibility = View.INVISIBLE
        EdtUserName.isEnabled = true
        EdtUserEmail.isEnabled = true
        Delete.visibility = View.INVISIBLE
    }

    private fun updateUser(studId: Int) {
        val userName = EdtUserName.text.toString()
        val userEmail = EdtUserEmail.text.toString()

        if (userName.isEmpty()) {
            EdtUserName.requestFocus()
            EdtUserName.error = "Please Enter Name"
            return
        }
        if (userEmail.isEmpty()) {
            EdtUserEmail.requestFocus()
            EdtUserEmail.error = "Please Enter Email"
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            EdtUserEmail.requestFocus()
            EdtUserEmail.error = "Please Enter Correct Email"
            return
        }


        var call: Call<UpdateResponse> = RetrofitClient()
                .getInstance()
                .getApi()
                .update(studId, userName, userEmail)
        call.enqueue(object : Callback<UpdateResponse> {
            override fun onResponse(call: Call<UpdateResponse>, response: Response<UpdateResponse>) {
                val updateResponse: UpdateResponse = response.body()!!

                if (response.isSuccessful) {
                    sharedPrefrenceManager.saveUser(updateResponse.user)
                    Toast.makeText(activity, updateResponse.msg, Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(activity, updateResponse.msg, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<UpdateResponse>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
                Log.d(RegistrationActivity.KEY, t.message.toString())
            }

        })
    }

    private fun deleteuser(studId: Int) {

        var call: Call<DeleteResponse> = RetrofitClient()
                .getInstance()
                .getApi()
                .delete(studId)
        call.enqueue(object : Callback<DeleteResponse> {
            override fun onResponse(call: Call<DeleteResponse>, response: Response<DeleteResponse>) {
                val deleteResponse: DeleteResponse = response.body()!!

                if (response.isSuccessful) {
                    sharedPrefrenceManager.sign_out()
                    var intent = Intent(activity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    Toast.makeText(activity, deleteResponse.msg, Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(activity, deleteResponse.msg, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_LONG).show()
                Log.d(RegistrationActivity.KEY, t.message.toString())
            }

        })
    }
}