package com.example.demoapp.view.homeandactionmenu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewReleasesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_releases)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,  NewReleasesFragment())
            .commit();
    }
}