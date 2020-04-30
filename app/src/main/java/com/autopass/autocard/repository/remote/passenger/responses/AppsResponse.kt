package com.autopass.autocard.repository.remote.passenger.responses

data class AppsResponse(
    val appStatus: Int,
    val appType: Int,
    val appVersion: Int,
    val creditType: Int,
    val expirationAppDate: String,
    val productId: Int
)