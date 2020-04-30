package com.autopass.autocard.core.extensions

fun ByteArray.toHexString(): String {
    return this.joinToString("") {
        String.format("%02x", it)
    }
}