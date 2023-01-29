package com.rzs.corroutinesproject.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rzs.corroutinesproject.R
import com.rzs.corroutinesproject.domain.model.Country
import com.rzs.corroutinesproject.presentation.recyclerview.retrofitrecyclerview.RetrofitViewAdapter
import com.rzs.corroutinesproject.presentation.viewmodel.RetrofitFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RetrofitFragment : Fragment() {
    private lateinit var viewModel: RetrofitFragmentViewModel
    private var countries : MutableList<Country> = ArrayList()
    private lateinit var recyclerview : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[RetrofitFragmentViewModel::class.java]
        val view = inflater.inflate(R.layout.fragment_retrofit, container, false)

        // getting the recyclerview by its id
        recyclerview = view.findViewById(R.id.rvr)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this.context)

        // This will pass the ArrayList to our Adapter
        val adapter = RetrofitViewAdapter(countries)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        viewModel.refresh()
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        // Inflate the layout for this fragment
        return view
    }


}