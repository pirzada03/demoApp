package com.example.demoapp.view.homeandactionmenu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.R

class HomeAndActionMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_and_action_menu)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,  HomeScreenFragment.newInstance("",""))
            .commit();

    }
}