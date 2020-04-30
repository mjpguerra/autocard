package com.autopass.autocard.core.nfc

import com.autopass.autocard.core.nfc.legacy.VL4MIF
import com.autopass.autocard.core.nfc.managers.CardManager
import com.autopass.autocard.core.nfc.managers.SerialDeviceManager

class NfcController(serialDeviceManager: SerialDeviceManager, vl: VL4MIF) : NfcFactory {
    override val serialDevice: SerialDeviceManager = serialDeviceManager
    override val card: CardManager = CardManager(vl)
}