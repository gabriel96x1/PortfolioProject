package com.rzs.corroutinesproject.presentation.view

import android.content.ComponentName
import android.content.Context
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.rzs.corroutinesproject.databinding.FragmentAndroidServicesBinding
import com.rzs.corroutinesproject.domain.services.BoundService
import com.rzs.corroutinesproject.domain.services.BoundService.MyBinder
import com.rzs.corroutinesproject.domain.services.ForegroundService
import kotlinx.coroutines.*


/**
 * A simple [Fragment] subclass.
 * Use the [AndroidServicesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AndroidServicesFragment : Fragment() {

    lateinit var binding: FragmentAndroidServicesBinding
    var mService: BoundService? = null

    // Boolean to check if our activity is bound to service or not
    var mIsBound: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAndroidServicesBinding.inflate(inflater, container, false)

        binding.foregroundServiceBtn.setOnClickListener {
            val foregroundServiceIntent = Intent(activity, ForegroundService::class.java)
            ContextCompat.startForegroundService(requireContext(), foregroundServiceIntent)
        }

        binding.boundServiceBtn.setOnClickListener {
            if (!mIsBound) {
                Log.d("bound service", "binding...")
                bindService()
                mIsBound = true
            } else {
                Log.d("bound service", "unbinding...")
                unbindService()
                mIsBound = false
            }
        }

        return binding.root
    }

    private fun getRandomNumberFromService() {
        mService?.randomNumberLiveData?.observe(this
        ) {
            binding.textViewBound.text = "Some random $it"
        }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, iBinder: IBinder) {
            Log.d("bound service", "ServiceConnection: connected from service.")
            // We've bound to MyService, cast the IBinder and get MyBinder instance
            val binder = BoundService().MyBinder()
            mService = binder.getService()
            mIsBound = true
            getRandomNumberFromService() // return a random number from the service
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            Log.d("bound service", "ServiceConnection: disconnected from service.")

            mIsBound = false
        }
    }

    private fun bindService() {
        Intent(activity, BoundService::class.java).also { intent ->
            requireActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    /**
     * Used to unbind and stop our service class
     */
    private fun unbindService() {
        Intent(activity, BoundService::class.java).also { intent ->
            requireActivity().unbindService(serviceConnection)
        }
    }
}