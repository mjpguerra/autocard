package com.autopass.autocard.presentation.card.actions

import com.autopass.autocard.core.nfc.NfcController
import com.autopass.autocard.core.nfc.cardblocks.AppBlock

class AppSR(private val nfcController: NfcController) {

    fun save(
        mainWalletBalance: String,
        mainWalletCounter: String,
        mainCounter: String,
        secondaryWalletBalance: String,
        secondaryWalletCounter: String,
        secondaryCounter: String
    ): Int {
        return nfcController.card.writeMainBlock(
            AppBlock(
                mainWalletBalance = mainWalletBalance.toInt(),
                mainWalletCounter = mainWalletCounter.toInt(),
                mainCounter = mainCounter.toInt() + 1,
                secondaryWalletBalance = secondaryWalletBalance.toInt(),
                secondaryWalletCounter = secondaryWalletCounter.toInt(),
                secondaryCounter = secondaryCounter.toInt() + 1
            )
        )
    }

    fun read(): AppBlock = nfcController.card.readMainBlock()
}