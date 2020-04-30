package com.autopass.autocard.repository.remote.passenger

import com.autopass.autocard.repository.remote.passenger.resources.RemotePassengerResources
import com.autopass.autocard.repository.remote.passenger.responses.CardDataResponse
import com.autopass.autocard.repository.remote.passenger.services.PassengerServices
import io.reactivex.Observable

import io.reactivex.Single
import retrofit2.Response

class RemotePassengerRepository(
    private val services: PassengerServices
): RemotePassengerResources {

    override fun cardConfirmation(cardId: String): Observable<Response<Any>> {
        return services.cardConfirmation(cardId)
    }

    override fun getCardData(documentNumber: String): Single<Response<List<CardDataResponse>>> {
        return services.getCardData(documentNumber)
    }


}