package com.autopass.autocard.core.nfc.utils

enum class CardBlocks(val byte: Byte) {
    CARD_ISSUE_DATA(8),
    CARD_STATUS(1),
    APP(2),
    RECHARGE_DATA(20),
    MAIN_APP_INFO(38),
    SECONDARY_APP_INFO(46)
}