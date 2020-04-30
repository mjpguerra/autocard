package com.autopass.autocard.presentation.card.actions

import com.autopass.autocard.core.nfc.NfcController
import com.autopass.autocard.core.nfc.cardblocks.StatusBlock
import org.joda.time.LocalDate

class StatusSR(private val nfcController: NfcController) {

    fun save(cardStatus: String, expirationDate: String, rfu: String): Int {
        return nfcController.card.writeStatusBlock(
            StatusBlock(
                cardStatus = cardStatus.toInt(),
                expirationDate = LocalDate.parse(expirationDate),
                rfu = rfu
            )
        )
    }

    fun read(): StatusBlock = nfcController.card.readStatusBlock()
}