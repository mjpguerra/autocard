package com.autopass.autocard.presentation.register.actions

import com.autopass.autocard.repository.Repository
import com.autopass.autocard.repository.remote.passenger.responses.CardDataResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Response

class GetDocumentNumber(private val repository: Repository) {

    fun execute(): Single<String> {
        return repository.local.general.getDocumentNumber()
    }
}