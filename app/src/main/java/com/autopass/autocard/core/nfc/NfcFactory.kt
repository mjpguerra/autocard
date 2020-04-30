package com.autopass.autocard.core.nfc

import com.autopass.autocard.core.nfc.managers.CardManager
import com.autopass.autocard.core.nfc.managers.SerialDeviceManager

interface NfcFactory {
    val serialDevice: SerialDeviceManager
    val card: CardManager
}