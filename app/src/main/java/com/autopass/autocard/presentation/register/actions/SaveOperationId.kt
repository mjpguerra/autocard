package com.autopass.autocard.presentation.register.actions

import com.autopass.autocard.repository.Repository
import com.autopass.autocard.repository.remote.passenger.responses.CardDataResponse
import io.reactivex.Single
import retrofit2.Response

class SaveOperationId(private val repository: Repository) {

    fun execute(response: Response<CardDataResponse>): Single<Response<CardDataResponse>> {
        val cardIssueDataResponse = response.body()?.cardIssueData
        return repository.local.general.saveOperationId(cardIssueDataResponse!!.operationId)
            .toSingleDefault(response)
    }
}