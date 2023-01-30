package com.rzs.corroutinesproject.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rzs.corroutinesproject.domain.NetworkRepository
import com.rzs.corroutinesproject.domain.model.AuthToken
import com.rzs.corroutinesproject.domain.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

@HiltViewModel
class RecyclerViewPaginationFragmentViewModel @Inject constructor(
    private val repository: NetworkRepository
): ViewModel() {
    private val _authToken = MutableLiveData<AuthToken>()

    val gamesList = MutableLiveData<MutableList<Game>>()

    private var currentResponse: MutableList<Game>? = mutableListOf()

    private lateinit var job: Job

    init {
        gamesList.value = mutableListOf()
    }

    fun getAuthToken() {
        job = viewModelScope.launch(Dispatchers.IO) {
            val request = repository.getToken()
            if (request.isSuccessful) {
                withContext(Dispatchers.Main) {
                    _authToken.value = request.body()
                    Log.e("AuthToken", request.body().toString())
                }
            }
        }
    }

    fun getGamesByPage(offset: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (job.isActive) {
                job.join()
            }
            val requestBody = "$PREFIX_GAMES_BODY_REQUEST$offset;".toRequestBody("text/plain".toMediaType())
            Log.d("Body", "$PREFIX_GAMES_BODY_REQUEST$offset;")
            val request = repository.getGames(
                "$PREFIX_AUTH_TOKEN${_authToken.value?.token.orEmpty()}",
                requestBody
            )
            if (request.isSuccessful) {
                withContext(Dispatchers.Main) {
                    currentResponse = request.body()
                    updateList(currentResponse)
                    Log.d("RequestBody", request.body().toString())
                    Log.d("GamesList", gamesList.value.toString())
                }
            }
        }
    }

    private fun updateList(response: MutableList<Game>?) {
        if (gamesList.value == null) {
            gamesList.value = response.orEmpty().toMutableList()
        } else {
            gamesList.value!!.addAll(response.orEmpty().toMutableList())
            val current = gamesList.value
            gamesList.value = current.orEmpty().toMutableList()
        }
    }

    companion object {
        const val PREFIX_GAMES_BODY_REQUEST = "fields name,summary,url; limit 10; offset "
        const val PREFIX_AUTH_TOKEN = "Bearer "
    }
}