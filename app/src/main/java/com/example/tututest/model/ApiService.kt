package com.example.tututest.model

import android.content.Context
import android.net.ConnectivityManager
import com.example.tututest.App
import com.example.tututest.model.dt.CharactersDT
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


private const val BASE_URL= "https://gateway.marvel.com/"

class ForceCacheInterceptor : Interceptor {
    private val connectivityManager = App.appContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        if (connectivityManager.activeNetwork == null) {
            builder.cacheControl(CacheControl.FORCE_CACHE)
        }
        return chain.proceed(builder.build())
    }
}

var cacheSize = 5 * 1024 * 1024

val cache = App.appContext?.cacheDir?.let { Cache(it, cacheSize.toLong()) }

var client: OkHttpClient = OkHttpClient.Builder().cache(cache).addInterceptor(ForceCacheInterceptor()).build()

private val retrofit = Retrofit.Builder()
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL).build()

interface ApiService {
    @GET("v1/public/characters?ts=1&apikey=1cadb66a117fd6e06d0e32e09bfb3bed&hash=8ffb610d5c9aafa787f5aaa1bd4c6548")
    suspend fun getCharacters(): CharactersDT
}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}