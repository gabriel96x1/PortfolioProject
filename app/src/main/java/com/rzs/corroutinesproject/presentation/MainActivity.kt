package com.rzs.corroutinesproject.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.rzs.corroutinesproject.presentation.view.MainScreenFragment
import com.rzs.corroutinesproject.presentation.view.RetrofitFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.rzs.corroutinesproject.R.layout.activity_main)
        val frag = MainScreenFragment()

        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(com.rzs.corroutinesproject.R.id.fragmentContainerView, frag)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}