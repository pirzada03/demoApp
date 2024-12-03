package com.example.demoapp.view.onboarding.splashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.R


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        if (savedInstanceState == null) {
            // Load SplashScreenFragment into the container
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SplashScreenFragment())
                .commit()
        }
    }
}

