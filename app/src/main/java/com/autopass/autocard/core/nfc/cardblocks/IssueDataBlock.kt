package com.autopass.autocard.core.nfc.cardblocks

import com.autopass.autocard.core.extensions.padToBinaryString
import com.autopass.autocard.core.extensions.toBinaryJulianDate
import com.autopass.autocard.core.nfc.cardblocks.interfaces.CardBlockInterface
import org.joda.time.LocalDate

data class IssueDataBlock(
    val issuerId: Int,
    val emissionDate: LocalDate,
    val cardType: Int,
    val cardSerialNumber: Int,//rsn
    val mapVersion: Int,
    val operationId: Int,
    val signature: String
) : CardBlockInterface {

    override fun toBinaryString(): String {
        return issuerId.padToBinaryString(8) +
                emissionDate.toBinaryJulianDate(16) +
                cardType.padToBinaryString(8) +
                cardSerialNumber.padToBinaryString(30) +
                mapVersion.padToBinaryString(6) +
                operationId.padToBinaryString(10) +
                signature.padStart(50, '0')
    }
}