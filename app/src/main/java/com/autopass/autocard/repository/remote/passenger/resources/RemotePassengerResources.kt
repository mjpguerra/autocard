package com.autopass.autocard.repository.remote.passenger.resources

import com.autopass.autocard.repository.remote.passenger.responses.CardDataResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

interface RemotePassengerResources {
    fun getCardData(documentNumber: String): Single<Response<List<CardDataResponse>>>
    fun cardConfirmation(cardId : String) : Observable<Response<Any>>
}