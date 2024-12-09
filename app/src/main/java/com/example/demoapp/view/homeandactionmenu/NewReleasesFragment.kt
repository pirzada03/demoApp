package com.example.demoapp.view.homeandactionmenu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.adapters.ExpandedRecyclerViewAdapter
import com.example.demoapp.data.ApiError
import com.example.demoapp.data.ApiLoading
import com.example.demoapp.data.ApiSuccess
import com.example.demoapp.databinding.FragmentTopMoviesBinding
import com.example.demoapp.viewmodel.NewReleaseViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


@AndroidEntryPoint
class NewReleasesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentTopMoviesBinding
    private lateinit var recyclerViewAdapter2: ExpandedRecyclerViewAdapter
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
        val view = inflater.inflate(R.layout.fragment_new_releases, container, false)
        val backArrow = view.findViewById<ImageView>(R.id.back)

        backArrow.setOnClickListener {
            val intent = Intent(requireContext(), HomeAndActionMenuActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
            requireActivity().finish()
        }

        return view

    }

    private fun initRecycleView1() {
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.rvTopMovies)
        recyclerView?.layoutManager = GridLayoutManager(context,2)
        recyclerViewAdapter2 = ExpandedRecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newReleaseViewModel = ViewModelProvider(this).get(newReleaseViewModel::class.java)
        initRecycleView1()
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
        newReleaseViewModel.makeApiCall()

    }
}

