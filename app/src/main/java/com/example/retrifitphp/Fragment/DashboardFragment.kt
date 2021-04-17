package com.example.retrifitphp.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.retrifitphp.R
import com.example.retrifitphp.SharedPreference.SharedPreferenceManager


class DashboardFragment : Fragment() {

    lateinit var mname: TextView
    lateinit var memail: TextView


    lateinit var sharedPreferencesManager: SharedPreferenceManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mname = view.findViewById(R.id.dname)
        memail = view.findViewById(R.id.demail)

        sharedPreferencesManager = SharedPreferenceManager(activity!!)
        mname.text = sharedPreferencesManager.get_user().Stud_name
        memail.text = sharedPreferencesManager.get_user().Email

    }
}