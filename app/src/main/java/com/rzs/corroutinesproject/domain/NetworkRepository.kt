package com.rzs.corroutinesproject.domain

import com.rzs.corroutinesproject.domain.model.AuthToken
import com.rzs.corroutinesproject.domain.model.Country
import com.rzs.corroutinesproject.domain.model.Game
import okhttp3.RequestBody
import retrofit2.Response

interface NetworkRepository {

    suspend fun getCountries(): Response<List<Country>>

    suspend fun getToken(): Response<AuthToken>

    suspend fun getGames(authToken: String, body: RequestBody): Response<MutableList<Game>>
}