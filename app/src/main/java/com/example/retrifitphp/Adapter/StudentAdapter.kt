package com.example.retrifitphp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrifitphp.ModelClass.User
import com.example.retrifitphp.R

class StudentAdapter(var context: Context,val studentList:ArrayList<User>): RecyclerView.Adapter<StudentAdapter.studentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): studentViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.user_data, parent, false)
        return studentViewHolder(view)

    }

    override fun onBindViewHolder(holder: studentViewHolder, position: Int) {
        holder.name!!.text = studentList.get(position).Stud_name
        holder.email!!.text = studentList.get(position).Email

    }

    override fun getItemCount(): Int {
        return studentList.size
    }
    class studentViewHolder(var itemView: View):RecyclerView.ViewHolder(itemView){
        var email: TextView? = itemView.findViewById(R.id.name)
        var name: TextView? = itemView.findViewById(R.id.email)
    }




}