package com.autopass.autocard.core.nfc.cardblocks.producers

import com.autopass.autocard.core.extensions.binaryToInt
import com.autopass.autocard.core.extensions.binaryToJulianDate
import com.autopass.autocard.core.nfc.cardblocks.*

fun String.toIssueDataBlock(): IssueDataBlock {
    val issuer = substring(0, 8).binaryToInt()
    val emissionDate = substring(8, 24).binaryToJulianDate()
    val cardType = substring(24, 32).binaryToInt()
    val cardSerialNumber = substring(32, 62).binaryToInt()
    val mapVersion = substring(62, 68).binaryToInt()
    val operationId = substring(68, 78).binaryToInt()
    val signature = substring(78)

    return IssueDataBlock(
        issuer,
        emissionDate,
        cardType,
        cardSerialNumber,
        mapVersion,
        operationId,
        signature
    )
}

fun String.toStatusBlock(): StatusBlock {
    val cardStatus = substring(0, 4).binaryToInt()
    val expirationDate = substring(4, 20).binaryToJulianDate()
    val rfu = substring(20)

    return StatusBlock(cardStatus, expirationDate, rfu)
}

fun String.toAppBlock(): AppBlock {
    val mainWalletBalance = substring(0, 24).binaryToInt()
    val mainWalletCounter = substring(24, 40).binaryToInt()
    val mainCounter = substring(40, 64).binaryToInt()
    val secondaryWalletBalance = substring(64, 88).binaryToInt()
    val secondaryWalletCounter = substring(88, 104).binaryToInt()
    val secondaryCounter = substring(104).binaryToInt()

    return AppBlock(
        mainWalletBalance,
        mainWalletCounter,
        mainCounter,
        secondaryWalletBalance,
        secondaryWalletCounter,
        secondaryCounter
    )
}

fun String.toInfoBlock(): InfoBlock {
    val creditType = substring(0, 4).binaryToInt()
    val appType = substring(4, 12).binaryToInt()
    val appVersion = substring(12, 22).binaryToInt()
    val creationDate = substring(22, 38).binaryToJulianDate()
    val appStatus = substring(38, 42).binaryToInt()
    val expirationAppDate = substring(42, 58).binaryToJulianDate()
    val lastUseDate = substring(58, 74).binaryToJulianDate()
    val dailyUse = substring(74, 84).binaryToInt()
    val monthlyUse = substring(84, 100).binaryToInt()
    val rfu = substring(100)

    return InfoBlock(
        creditType,
        appType,
        appVersion,
        creationDate,
        appStatus,
        expirationAppDate,
        lastUseDate,
        dailyUse,
        monthlyUse,
        rfu
    )
}

fun String.toRechargeDataBlock(): RechargeDataBlock {
    val rechargeDate = substring(0, 16).binaryToJulianDate()
    val rechargedBalance = substring(16, 36).binaryToInt()
    val creditsSerie = substring(36, 56).binaryToInt()
    val rfu = substring(56, 76)
    val signature = substring(76)

    return RechargeDataBlock(rechargeDate, rechargedBalance, creditsSerie, rfu, signature)
}