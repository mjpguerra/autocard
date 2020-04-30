package com.autopass.autocard.presentation.card.actions

import com.autopass.autocard.core.nfc.NfcController

class WaitCardRemove(private val nfcController: NfcController) {

    fun execute() {
        nfcController.serialDevice.waitCardRemove()
    }
}