package com.autopass.autocard.repository.remote.passenger.responses

data class CardIssueDataResponse(
    val cardType: Int,
    val csn: String,
    val emissionDate: String,
    val issuerId: Int,
    val mapVersion: Int,
    val operationId: Int,
    val passengerId: Int,
    val cardSeq: String,
    val cardId: String
)