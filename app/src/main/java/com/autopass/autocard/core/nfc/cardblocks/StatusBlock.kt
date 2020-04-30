package com.autopass.autocard.core.nfc.cardblocks

import com.autopass.autocard.core.extensions.padToBinaryString
import com.autopass.autocard.core.extensions.toBinaryJulianDate
import com.autopass.autocard.core.nfc.cardblocks.interfaces.CardBlockInterface
import org.joda.time.LocalDate

data class StatusBlock(
    val cardStatus: Int,
    val expirationDate: LocalDate,
    val rfu: String
) : CardBlockInterface {

    override fun toBinaryString(): String {
        return cardStatus.padToBinaryString(4) +
                expirationDate.toBinaryJulianDate(16) +
                rfu.padStart(108, '0')
    }
}