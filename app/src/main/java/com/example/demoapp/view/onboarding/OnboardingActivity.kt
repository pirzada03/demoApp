package com.example.demoapp.view.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.R
import com.example.demoapp.view.onboarding.splashscreen.SplashScreenFragment
import androidx.appcompat.widget.Toolbar

class OnboardingActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SplashScreenFragment())
                .commit()
        }

    }

}
