package com.autopass.autocard.core.nfc.cardblocks

import com.autopass.autocard.core.extensions.padToBinaryString
import com.autopass.autocard.core.extensions.toBinaryJulianDate
import com.autopass.autocard.core.nfc.cardblocks.interfaces.CardBlockInterface
import org.joda.time.LocalDate

class RechargeDataBlock(
    val rechargeDate: LocalDate,
    val rechargedBalance: Int,
    val creditSeries: Int,
    val rfu: String,
    val signature: String
) : CardBlockInterface {

    override fun toBinaryString(): String {
        return rechargeDate.toBinaryJulianDate(16) +
                rechargedBalance.padToBinaryString(20) +
                creditSeries.padToBinaryString(20) +
                rfu.padStart(20, '0') +
                signature.padStart(52, '0')
    }
}