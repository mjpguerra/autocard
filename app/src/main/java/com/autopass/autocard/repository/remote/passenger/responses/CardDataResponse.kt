package com.autopass.autocard.repository.remote.passenger.responses

import java.io.Serializable

data class CardDataResponse (
    val apps: List<AppsResponse>,
    val cardIssueData: CardIssueDataResponse,
    val cardStatus: CardStatusResponse
) : Serializable