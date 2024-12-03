package com.example.demoapp.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.R
import com.example.demoapp.view.onboarding.splashscreen.SplashScreenFragment
import com.example.demoapp.view.onboarding.welcomescreen.WelcomeScreen

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, WelcomeScreen())
            .commit()
    }
}