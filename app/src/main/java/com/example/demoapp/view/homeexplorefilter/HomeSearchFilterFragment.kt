package com.example.demoapp.view.homeexplorefilter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.adapters.ExpandedRecyclerViewAdapter
import com.example.demoapp.data.ApiError
import com.example.demoapp.data.ApiLoading
import com.example.demoapp.data.ApiSuccess
import com.example.demoapp.view.download.DownloadActivity
import com.example.demoapp.view.homeandactionmenu.HomeAndActionMenuActivity
import com.example.demoapp.view.mylist.MyListActivity
import com.example.demoapp.viewmodel.TopRatedViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeSearchFilterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class HomeSearchFilterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var recyclerViewAdapter: ExpandedRecyclerViewAdapter
    private lateinit var beforeSearchViewModel: TopRatedViewModel

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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home_search_filter, container, false)

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

    private fun startActivityWithReorder(activityClass: Class<*>) {
        val intent = Intent(requireContext(), activityClass)
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        startActivity(intent)
    }

    private fun initRecycleView1() {
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.rv_explore)
        recyclerView?.layoutManager = GridLayoutManager(context,2)
        recyclerViewAdapter = ExpandedRecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        beforeSearchViewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)
        initRecycleView1()
        beforeSearchViewModel.getMoviesLiveData().observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiLoading -> {
                    // Show loading indicator (if you have one)
                }
                is ApiSuccess -> {
                    Log.d(tag,"Result is: $result")
                    result.data?.results?.let { movies ->
                        recyclerViewAdapter.setUpdatedData(movies)
                        recyclerViewAdapter.notifyDataSetChanged()
                    }
                }
                is ApiError -> {
                    // Show error message
                    Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        beforeSearchViewModel.makeApiCall(1)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment homeSearchFilterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeSearchFilterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}