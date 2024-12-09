package com.example.demoapp.view.homeandactionmenu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.adapters.RecyclerViewAdapter
import com.example.demoapp.data.ApiError
import com.example.demoapp.data.ApiLoading
import com.example.demoapp.data.ApiSuccess
import com.example.demoapp.view.download.DownloadActivity
import com.example.demoapp.view.homeexplorefilter.HomeExploreFilterActivity
import com.example.demoapp.view.mylist.MyListActivity
import com.example.demoapp.viewmodel.NewReleaseViewModel
import com.example.demoapp.viewmodel.TopRatedViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
@AndroidEntryPoint
class HomeScreenFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerViewAdapter1: RecyclerViewAdapter
    private lateinit var recyclerViewAdapter2: RecyclerViewAdapter

    private lateinit var topRatedViewModel:TopRatedViewModel
    private lateinit var newReleaseViewModel: NewReleaseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)

        val tvSeeAll1: TextView = view.findViewById<TextView>(R.id.see_all1);
        val tvSeeAll2: TextView = view.findViewById<TextView>(R.id.see_all2);

        tvSeeAll1.setOnClickListener {
            val intent = Intent(requireContext(), TopMoviesActivity::class.java)
            startActivity(intent)
        }

        tvSeeAll2.setOnClickListener {
            val intent = Intent(requireContext(), NewReleasesActivity::class.java)
            startActivity(intent)

        }
        val bottomNav: BottomNavigationView = view.findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    startActivityWithReorder(HomeAndActionMenuActivity::class.java)
                    true
                }
                R.id.explore -> {
                    startActivityWithReorder(HomeExploreFilterActivity::class.java)
                    true
                }
                R.id.myList -> {
                    startActivityWithReorder(MyListActivity::class.java)
                    true
                }
                R.id.download -> {
                    startActivityWithReorder(DownloadActivity::class.java)
                    true
                }
                else -> false
            }
        }

        return view
    }

    private fun initRecycleView1() {
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView1)
        recyclerView?.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewAdapter1 = RecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter1
    }

    private fun initRecycleView2() {
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView2)
        recyclerView?.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewAdapter2 = RecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter2
    }

    private fun startActivityWithReorder(activityClass: Class<*>) {
        val intent = Intent(requireContext(), activityClass)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topRatedViewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)
        initRecycleView1()
        newReleaseViewModel = ViewModelProvider(this).get(NewReleaseViewModel::class.java)
        initRecycleView2()

        topRatedViewModel.getMoviesLiveData().observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiLoading -> {
                    // Show loading indicator (if you have one)
                }
                is ApiSuccess -> {
                    Log.d(tag,"Result is: $result")
                    result.data?.results?.let { movies ->
                        recyclerViewAdapter1.setUpdatedData(movies)
                        recyclerViewAdapter1.notifyDataSetChanged()
                    }
                }
                is ApiError -> {
                    // Show error message
                    Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        newReleaseViewModel.getNewMoviesLiveData().observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiLoading -> {
                    // Show loading indicator (if you have one)
                }
                is ApiSuccess -> {
                    Log.d(tag,"Result is: $result")
                    result.data?.results?.let { movies ->
                        recyclerViewAdapter2.setUpdatedData(movies)
                        recyclerViewAdapter2.notifyDataSetChanged()
                    }
                }
                is ApiError -> {
                    // Show error message
                    Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        topRatedViewModel.makeApiCall(0)
        newReleaseViewModel.makeApiCall()

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}