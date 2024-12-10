package com.example.demoapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.demoapp.R
import com.example.demoapp.databinding.ActivityMainBinding
import com.example.demoapp.view.download.DownloadFragment
import com.example.demoapp.view.homeandactionmenu.HomeScreenFragment
import com.example.demoapp.view.homeexplorefilter.HomeSearchFilterFragment
import com.example.demoapp.view.mylist.MyListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeScreenFragment(),"HomeScreenFragment")


            binding.bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    replaceFragment(HomeScreenFragment(),"HomeScreenFragment")
                    true
                }
                R.id.explore -> {
                    replaceFragment(HomeSearchFilterFragment(),"HomeSearchFilterFragment")
                    true
                }
                R.id.myList -> {
                    replaceFragment(MyListFragment(),"MyListFragment")
                    true
                }
                R.id.download -> {
                    replaceFragment(DownloadFragment(),"DownloadFragment")
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment, tag:String) {
        val currentFragment = supportFragmentManager.findFragmentByTag(tag)
        if(currentFragment==null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment,tag)
                .commit()
        }
    }
}