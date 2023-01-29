package com.rzs.corroutinesproject.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rzs.corroutinesproject.domain.NetworkRepository
import com.rzs.corroutinesproject.domain.model.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class RetrofitFragmentViewModel @Inject constructor(
    private val repository: NetworkRepository
) : ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryLoadError =  MutableLiveData<String?>()
    val loading =  MutableLiveData<Boolean>()
    
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext: CoroutineContext, throwable: Throwable ->
        onError("retrofit call: ${throwable.localizedMessage}")
    }

    fun refresh() {
        getCountries()
    }

    private fun getCountries() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = repository.getCountries()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    countries.value = response.body()
                    countryLoadError.value = null
                    loading.value = false
                    Log.d("response", countries.value.toString())
                } else {
                    onError("retrofit call: ${response.message()}")
                    Log.d("response", countries.value.toString())
                }
            }
        }
    }

    private fun onError(message: String) {
        countryLoadError.value = message
        loading.value = false
    }
}