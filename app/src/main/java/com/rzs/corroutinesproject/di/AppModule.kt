package com.rzs.corroutinesproject.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.room.RoomDatabase
import androidx.viewbinding.BuildConfig
import com.rzs.corroutinesproject.App
import com.rzs.corroutinesproject.data.local.RoomRepositoryImpl
import com.rzs.corroutinesproject.data.local.UserDao
import com.rzs.corroutinesproject.data.local.UserDatabase
import com.rzs.corroutinesproject.data.remote.NetworkRepoImpl
import com.rzs.corroutinesproject.data.remote.NetworkService
import com.rzs.corroutinesproject.domain.NetworkRepository
import com.rzs.corroutinesproject.domain.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val cacheSize = (5 * 1024 * 1024).toLong()

    @Provides
    fun provideBaseUrl() = "https://raw.githubusercontent.com/"

    const val TWITCH_AUTH_URL = "https://id.twitch.tv/oauth2/token?"
    const val TWITCH_GAMES_URL = "https://api.igdb.com/v4/games/"

    @Provides
    fun context(@ApplicationContext appContext: Context): Context = appContext

    @Singleton
    @Provides
    fun myCache(context: Context) = Cache(context.cacheDir, cacheSize)

    @Singleton
    @Provides
    fun isOffline(context: Context): Boolean {
        val cm = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo == null || !netInfo.isConnected
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(myCache: Cache, isOffline: Boolean) = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (!isOffline)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 50).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): NetworkService = retrofit.create(NetworkService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(networkRepoImpl: NetworkRepoImpl): NetworkRepository = networkRepoImpl

    @Provides
    @Singleton
    fun provideRoomDB(context: Context): UserDatabase = UserDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideUserRepository(userDatabase: UserDatabase): RoomRepository = RoomRepositoryImpl(userDatabase.userDao())

}