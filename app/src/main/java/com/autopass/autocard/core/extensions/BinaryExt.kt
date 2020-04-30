package com.autopass.autocard.core.extensions

import com.autopass.autocard.core.exceptions.BinaryStringException
import org.joda.time.Days
import org.joda.time.LocalDate

private val BINARY_PATTERN: Regex = "^[0-1]+\$".toRegex()
private val INITIAL_JULIAN_DATE: LocalDate = LocalDate(2000, 1, 1)

fun String.binaryToInt(): Int {
    return if (matches(BINARY_PATTERN)) Integer.parseInt(this, 2)
    else throw BinaryStringException()
}

fun LocalDate.toBinaryJulianDate(size: Int): String {
    return Days.daysBetween(INITIAL_JULIAN_DATE, this).days
        .padToBinaryString(size)
}

fun String.binaryToJulianDate(): LocalDate {
    if (!matches(BINARY_PATTERN)) throw BinaryStringException()
    val days = Integer.parseInt(this, 2)
    return INITIAL_JULIAN_DATE.plusDays(days)
}

fun Int.padToBinaryString(size: Int): String {
    return toString(2)
        .padStart(size, '0')
}

fun String.blockToByteArray(blockSize: Int): ByteArray {
    val formattedString = padEnd(128, '0')
    val blockByteArray = ByteArray(16 * blockSize)
    for (i in 0 until 16 * blockSize) {
        val byteString = formattedString.substring(8 * i, 8 * (i + 1))
        blockByteArray[i] = Integer.parseInt(byteString, 2).toByte()
    }
    return blockByteArray
}

fun ByteArray.toBlockString(blockSize: Int): String {
    var ret = ""
    if (size == 16 * blockSize) {
        for (i in 0 until 16 * blockSize) {
            var a = this[i].toInt()
            if (a < 0) a += 256
            ret += String.format("%8s", Integer.toBinaryString(a)).replace(' ', '0')
        }
    }
    return ret
}