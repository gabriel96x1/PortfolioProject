package com.rzs.corroutinesproject.data.remote

import com.rzs.corroutinesproject.BuildConfig
import com.rzs.corroutinesproject.di.AppModule
import com.rzs.corroutinesproject.domain.model.AuthToken
import com.rzs.corroutinesproject.domain.model.Country
import com.rzs.corroutinesproject.domain.model.Game
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface NetworkService {

    @GET("DevTides/countries/master/countriesV2.json")
    suspend fun getCountries(): Response<List<Country>>

    @FormUrlEncoded
    @POST(AppModule.TWITCH_AUTH_URL)
    suspend fun getToken(
        @Field("client_id") clientId: String = BuildConfig.CLIENT_ID,
        @Field("client_secret") clientSecret: String = BuildConfig.CLIENT_SECRET,
        @Field("grant_type") grantType: String = BuildConfig.GRANT_TYPE,
    ): Response<AuthToken>

    @POST(AppModule.TWITCH_GAMES_URL)
    suspend fun getGames(
        @Header("Client-ID") clientId: String = BuildConfig.CLIENT_ID,
        @Header("Authorization") authToken: String,
        @Body body: RequestBody
    ): Response<MutableList<Game>>
}