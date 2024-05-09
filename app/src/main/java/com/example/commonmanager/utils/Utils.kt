package com.example.commonmanager.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentTime(): String {
    val format = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault())
    return format.format(Date(System.currentTimeMillis()))
}