package com.sinue.pagatodo.mx.data.api

import com.sinue.pagatodo.mx.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

//    private const val BASE_URL = "https://storage.googleapis.com/recursos_app/Services/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URLTODOPAGO)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}