package com.rzs.corroutinesproject.data.remote

import com.rzs.corroutinesproject.domain.NetworkRepository
import okhttp3.RequestBody
import javax.inject.Inject

class NetworkRepoImpl @Inject constructor(
    private val service: NetworkService
) : NetworkRepository {

    override suspend fun getCountries() = service.getCountries()

    override suspend fun getToken() = service.getToken()

    override suspend fun getGames(
        authToken: String,
        body: RequestBody
    ) = service.getGames(
        authToken = authToken,
        body = body
    )
}