package com.autopass.autocard.core.extensions

fun String.toCardDateStringFormat(): String {
    return "${substring(0, 4)}-${substring(4, 6)}-${substring(6)}"
}