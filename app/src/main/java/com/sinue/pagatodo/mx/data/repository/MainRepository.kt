package com.sinue.pagatodo.mx.data.repository

import com.sinue.pagatodo.mx.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getTransactionsSuccesfully() = apiHelper.getTransactionsSuccesfully()
    suspend fun getTransactionsEmpty() = apiHelper.getTransactionsEmpty()
    suspend fun getTransactionsMalformed() = apiHelper.getTransactionsMalformed()

    enum class ResponseType{
        SUCCESS,
        EMPTY,
        MALFORMED
    }
}