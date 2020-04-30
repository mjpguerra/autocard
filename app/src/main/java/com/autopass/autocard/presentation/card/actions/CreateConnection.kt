package com.autopass.autocard.presentation.card.actions

import com.autopass.autocard.core.nfc.NfcController

class CreateConnection(private val nfcController: NfcController) {

    fun execute(serial: ByteArray) {
        nfcController.card.createConnection(serial)
    }
}