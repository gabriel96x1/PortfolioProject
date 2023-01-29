package com.rzs.corroutinesproject.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.rzs.corroutinesproject.R
import com.rzs.corroutinesproject.databinding.FragmentMainScreenBinding

class MainScreenFragment : Fragment() {
    private lateinit var binding: FragmentMainScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragRetrofit = RetrofitFragment()
        val fragRoom = RoomFragment()
        val fragServices = AndroidServicesFragment()
        val fragPagination = RecyclerViewPaginationFragment()
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        binding.roomNav.setOnClickListener {
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, fragRoom)
            transaction.addToBackStack("room")
            transaction.commit()
        }

        binding.retrofitNav.setOnClickListener {
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, fragRetrofit)
            transaction.addToBackStack("retro")
            transaction.commit()
        }

        binding.servicesNav.setOnClickListener {
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, fragServices)
            transaction.addToBackStack("serv")
            transaction.commit()
        }

        binding.retrofitWithPages.setOnClickListener {
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, fragPagination)
            transaction.addToBackStack("pagination")
            transaction.commit()
        }


        return binding.root
    }
}