package com.rzs.corroutinesproject.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rzs.corroutinesproject.databinding.FragmentRecyclerViewPaginationBinding
import com.rzs.corroutinesproject.domain.model.Game
import com.rzs.corroutinesproject.presentation.recyclerview.paginationrecyclerview.PaginationViewAdapter
import com.rzs.corroutinesproject.presentation.viewmodel.RecyclerViewPaginationFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecyclerViewPaginationFragment : Fragment() {
    private lateinit var binding: FragmentRecyclerViewPaginationBinding
    private var games : MutableList<Game> = mutableListOf(
        Game(
            id = 10,
            url = "some",
            name = "some",
            summary = "some"
        )
    )
    private lateinit var recyclerview : RecyclerView
    private lateinit var viewModel: RecyclerViewPaginationFragmentViewModel
    private var offset = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerViewPaginationBinding.inflate(inflater, container, false)
        // getting the recyclerview by its id
        viewModel = ViewModelProvider(this)[RecyclerViewPaginationFragmentViewModel::class.java]
        viewModel.getAuthToken()
        viewModel.getGamesByPage(offset)
        offset += 10
        setupUi()

        return binding.root
    }

    private fun setupUi() {
        recyclerview = binding.recyclerViewPagination

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this.context)

        // This will pass the ArrayList to our Adapter
        val adapter = PaginationViewAdapter(games)

        //Log.d("gamelist", viewModel.gamesList.value.toString())
        viewModel.gamesList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            Log.d("dataChanged", it.toString())
        }
        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        recyclerview.setOnScrollChangeListener { view, _, _, _, _ ->
            if (!view.canScrollVertically(1)) {
                Log.d("gamelist", viewModel.gamesList.value.toString())
                viewModel.getGamesByPage(offset)
                offset += 10
            }
        }
    }

}