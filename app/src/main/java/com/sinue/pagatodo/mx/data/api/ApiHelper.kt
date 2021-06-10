package com.sinue.pagatodo.mx.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getTransactionsSuccesfully() = apiService.getTransactionsSuccesfully()
    suspend fun getTransactionsEmpty() = apiService.getTransactionsEmpty()
    suspend fun getTransactionsMalformed() = apiService.getTransactionsMalformed()
}