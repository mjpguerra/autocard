package com.autopass.autocard.core.nfc.cardblocks

import com.autopass.autocard.core.extensions.padToBinaryString
import com.autopass.autocard.core.nfc.cardblocks.interfaces.CardBlockInterface

data class AppBlock(
    val mainWalletBalance: Int,
    val mainWalletCounter: Int,
    val mainCounter: Int,
    val secondaryWalletBalance: Int,
    val secondaryWalletCounter: Int,
    val secondaryCounter: Int
) : CardBlockInterface {

    override fun toBinaryString(): String {
        return mainWalletBalance.padToBinaryString(24) +
                mainWalletCounter.padToBinaryString(16) +
                mainCounter.padToBinaryString(24) +
                secondaryWalletBalance.padToBinaryString(24) +
                secondaryWalletCounter.padToBinaryString(16) +
                secondaryCounter.padToBinaryString(24)
    }
}