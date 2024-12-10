package com.example.demoapp.view.onboarding.splashscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demoapp.R
import com.example.demoapp.view.MainActivity
import com.example.demoapp.view.OnboardingActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SplashScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashScreenFragment : Fragment(R.layout.fragment_screen_splash) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        Handler(Looper.getMainLooper()).postDelayed({
            val sharedPref = requireActivity().getSharedPreferences(getString(R.string.str_preference_file_key), Context.MODE_PRIVATE)
            val isLoggedIn = sharedPref.getBoolean("isLoggedIn",false)


            val intent = if (isLoggedIn) {
                Intent(requireActivity(), MainActivity::class.java)
            } else {
                Intent(requireActivity(), OnboardingActivity::class.java)
            }
            startActivity(intent)
            requireActivity().finish() // Finish SplashScreenActivity
        }, 2000) // Delay for 2 seconds
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_screen_splash, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            val sharedPref = requireActivity().getSharedPreferences(getString(R.string.str_preference_file_key), Context.MODE_PRIVATE)
            val isLoggedIn = sharedPref.getBoolean("isLoggedIn",false)

            val intent = if (isLoggedIn) {
                Intent(requireActivity(), MainActivity::class.java)
            } else {
                Intent(requireActivity(), OnboardingActivity::class.java)
            }

            startActivity(intent)
            requireActivity().finish() // Finish SplashScreenActivity
        }, 2000) // Delay for 2 seconds

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SplashScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SplashScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}