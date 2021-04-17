package com.example.retrifitphp.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrifitphp.Adapter.StudentAdapter
import com.example.retrifitphp.ModelClass.User
import com.example.retrifitphp.R
import com.example.retrifitphp.ResponseModel.FatchAllStudentResponse
import com.example.retrifitphp.Retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserFragment : Fragment() {

    lateinit var studentRecyclerView:RecyclerView
    //lateinit var userList: ArrayList<User>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentRecyclerView = view.findViewById(R.id.studentrecycleView)

        var call:Call<FatchAllStudentResponse> = RetrofitClient()
                .getInstance()
                .getApi()
                .fatchAllStudent()

        call.enqueue(object : Callback<FatchAllStudentResponse> {
            override fun onResponse(call: Call<FatchAllStudentResponse>, response: Response<FatchAllStudentResponse>) {
                if (response.isSuccessful) {
                    var studentResponse: FatchAllStudentResponse = response.body()!!
                    if (studentResponse.error == 0) {
                        var studentList: ArrayList<User> = studentResponse.studentList
                        studentRecyclerView.adapter = StudentAdapter(activity!!, studentList)
                    } else {
                        Toast.makeText(activity, "No Data Found", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<FatchAllStudentResponse>, t: Throwable) {
                Toast.makeText(activity, t.message.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }


}