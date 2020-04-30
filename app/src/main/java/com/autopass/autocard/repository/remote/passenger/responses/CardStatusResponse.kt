package com.autopass.autocard.repository.remote.passenger.responses

data class CardStatusResponse(
    val expirationDate: String,
    val status: Int
)