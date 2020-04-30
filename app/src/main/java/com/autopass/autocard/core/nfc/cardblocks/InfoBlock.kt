package com.autopass.autocard.core.nfc.cardblocks

import com.autopass.autocard.core.extensions.padToBinaryString
import com.autopass.autocard.core.extensions.toBinaryJulianDate
import com.autopass.autocard.core.nfc.cardblocks.interfaces.CardBlockInterface
import org.joda.time.LocalDate

data class InfoBlock(
    val creditType: Int,
    val appType: Int,
    val appVersion: Int,
    val creationDate: LocalDate,
    val appStatus: Int,
    val expirationAppDate: LocalDate,
    val lastUseDate: LocalDate,
    val dailyUse: Int,
    val monthlyUse: Int,
    val rfu: String
) : CardBlockInterface {

    override fun toBinaryString(): String {
        return creditType.padToBinaryString(4) +
                appType.padToBinaryString(8) +
                appVersion.padToBinaryString(10) +
                creationDate.toBinaryJulianDate(16) +
                appStatus.padToBinaryString(4) +
                expirationAppDate.toBinaryJulianDate(16) +
                lastUseDate.toBinaryJulianDate(16) +
                dailyUse.padToBinaryString(10) +
                monthlyUse.padToBinaryString(16) +
                rfu.padStart(28, '0')
    }
}