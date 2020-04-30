package com.autopass.autocard.presentation.card.actions

import com.autopass.autocard.core.nfc.NfcController
import com.autopass.autocard.core.nfc.cardblocks.IssueDataBlock
import org.joda.time.LocalDate

class IssueDataSR(private val nfcController: NfcController) {

    fun save(
        issuerId: String,
        emissionDate: String,
        cardType: String,
        cardSerialNumber: String,
        mapVersion: String,
        operationId: String,
        signature: String
    ): Int {
        return nfcController.card.writeIssueDataBlock(
            IssueDataBlock(
                issuerId = issuerId.toInt(),
                emissionDate = LocalDate(emissionDate.substring(0, 10)),
                cardType = cardType.toInt(),
                cardSerialNumber = cardSerialNumber.toInt(),
                mapVersion = mapVersion.toInt(),
                operationId = operationId.toInt(),
                signature = signature
            )
        )
    }

    fun read(): IssueDataBlock = nfcController.card.readIssueDataBlock()
}