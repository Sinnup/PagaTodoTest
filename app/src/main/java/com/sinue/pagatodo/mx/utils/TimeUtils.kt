package com.sinue.pagatodo.mx.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {

    fun convertTimeStampMillisToLocal(timestamp: Long): String? {

        val simpleDateFormat =
            SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("Spanish", "Mexico"))
        val dateString = simpleDateFormat.format(timestamp * 1000)
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"))
        val date: Date? = simpleDateFormat.parse(dateString)
        simpleDateFormat.setTimeZone(TimeZone.getDefault())
        var formatedDate: String? = null
        date?.let {
            formatedDate = simpleDateFormat.format(it)
        }
        return formatedDate

    }
}