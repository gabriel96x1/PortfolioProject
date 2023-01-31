package com.rzs.corroutinesproject.presentation.view

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
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
    private lateinit var progressBar : ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[RetrofitFragmentViewModel::class.java]
        val view = inflater.inflate(R.layout.fragment_retrofit, container, false)

        // getting the recyclerview by its id
        recyclerview = view.findViewById(R.id.rvr)
        progressBar = view.findViewById(R.id.indeterminateBar)

        setupProgressBar()
        setupRecyclerView()

        return view
    }

    private fun setupProgressBar() {
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it)
                progressBar.visibility = View.VISIBLE
            else
                progressBar.visibility = View.GONE
        })

    }

    private fun setupRecyclerView() {
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
    }
}