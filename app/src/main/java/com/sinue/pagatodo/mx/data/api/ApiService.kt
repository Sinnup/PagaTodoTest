package com.sinue.pagatodo.mx.data.api

import com.sinue.pagatodo.mx.data.model.UserTransactionListResponse
import retrofit2.http.GET

interface ApiService {

    @GET("pruebaFile.json")
    suspend fun getTransactionsSuccesfully(): UserTransactionListResponse

    @GET("servicioVacio.json")
    suspend fun getTransactionsEmpty(): UserTransactionListResponse

    @GET("pruebaMalFormada.json")
    suspend fun getTransactionsMalformed(): UserTransactionListResponse
}