package com.autopass.autocard.presentation.card.actions

import com.autopass.autocard.core.nfc.NfcController
import com.autopass.autocard.core.nfc.cardblocks.InfoBlock
import com.autopass.autocard.core.nfc.utils.CardBlocks

import org.joda.time.LocalDate

class SecondaryInfoSR(private val nfcController: NfcController) {

    fun save(
        creditType: String,
        appType: String,
        appVersion: String,
        creationDate: String,
        appStatus: String,
        expirationAppDate: String,
        lastUseDate: String,
        dailyUse: String,
        monthlyUse: String,
        rfu: String
    ): Int {
        return nfcController.card.writeInfoBlock(
            InfoBlock(
                creditType = creditType.toInt(),
                appType = appType.toInt(),
                appVersion = appVersion.toInt(),
                creationDate = LocalDate(creationDate),
                appStatus = appStatus.toInt(),
                expirationAppDate = LocalDate(expirationAppDate),
                lastUseDate = LocalDate(lastUseDate),
                dailyUse = dailyUse.toInt(),
                monthlyUse = monthlyUse.toInt(),
                rfu = rfu
            ),
            CardBlocks.SECONDARY_APP_INFO
        )
    }

    fun read(): InfoBlock = nfcController.card.readInfoBlock(CardBlocks.SECONDARY_APP_INFO)
}