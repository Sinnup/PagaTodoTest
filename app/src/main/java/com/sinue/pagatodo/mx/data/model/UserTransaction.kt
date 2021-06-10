package com.sinue.pagatodo.mx.data.model

data class UserTransaction(
    var uuid: String,
    var merchantUuid: String,
    var merchantName: String,
    var currencyCode: String,
    var amount: Int,
    var timestamp: Long
)


