package com.rzs.corroutinesproject.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rzs.corroutinesproject.R
import com.rzs.corroutinesproject.databinding.FragmentMainScreenBinding
import com.rzs.corroutinesproject.databinding.FragmentRoomBinding
import com.rzs.corroutinesproject.domain.model.Country
import com.rzs.corroutinesproject.domain.model.User
import com.rzs.corroutinesproject.presentation.recyclerview.retrofitrecyclerview.RetrofitViewAdapter
import com.rzs.corroutinesproject.presentation.recyclerview.roomrecyclerview.RoomViewAdapter
import com.rzs.corroutinesproject.presentation.viewmodel.RetrofitFragmentViewModel
import com.rzs.corroutinesproject.presentation.viewmodel.RoomFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment : Fragment() {
    private lateinit var viewModel: RoomFragmentViewModel
    private lateinit var binding: FragmentRoomBinding
    private var users : MutableList<User> = ArrayList()
    private lateinit var recyclerview : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[RoomFragmentViewModel::class.java]
        binding = FragmentRoomBinding.inflate(inflater, container, false)

        // getting the recyclerview by its id
        recyclerview = binding.recyclerRoom

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this.context)

        // This will pass the ArrayList to our Adapter
        val adapter = RoomViewAdapter(users)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        return binding.root
    }
}