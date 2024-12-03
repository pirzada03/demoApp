package com.example.demoapp.view.homeandactionmenu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.demoapp.R

class TopMoviesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_top_movies)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,  TopMoviesFragment())
            .commit();
    }
}