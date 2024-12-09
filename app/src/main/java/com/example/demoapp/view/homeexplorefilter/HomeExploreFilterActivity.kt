package com.example.demoapp.view.homeexplorefilter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.demoapp.R
import com.example.demoapp.view.homeandactionmenu.HomeScreenFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeExploreFilterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_explore_filter)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,  HomeSearchFilterFragment.newInstance("",""))
            .commit();
    }
}