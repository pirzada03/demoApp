package com.example.demoapp.view.homeandactionmenu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.demoapp.R
import com.example.demoapp.adapters.RecyclerViewAdapter
import com.example.demoapp.viewmodel.TopRatedViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeAndActionMenuActivity : AppCompatActivity() {
//    private lateinit var topRatedViewModel : TopRatedViewModel
//
//    private val movieAdapter = RecyclerViewAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        topRatedViewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)
        setContentView(R.layout.activity_home_and_action_menu)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,  HomeScreenFragment.newInstance("",""))
            .commit();

    }
}