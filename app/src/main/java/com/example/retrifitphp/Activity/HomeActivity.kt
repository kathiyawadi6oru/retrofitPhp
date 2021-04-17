package com.example.retrifitphp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.retrifitphp.Fragment.DashboardFragment
import com.example.retrifitphp.Fragment.ProfileFragment
import com.example.retrifitphp.Fragment.UserFragment
import com.example.retrifitphp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener  {

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        loadFragment(DashboardFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment : Fragment? = null
        when(item.itemId) {
            R.id.bottom_menu_dashboard -> {
                fragment = DashboardFragment()
            }
            R.id.bottom_menu_user -> {
                fragment = UserFragment()
            }
            R.id.bottom_menu_profile -> {
                fragment = ProfileFragment()
            }
        }
            if (fragment!=null){
             loadFragment(fragment)
            }
            return true
        }


    fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.relativeLayout,fragment).commit()
    }
}