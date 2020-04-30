package com.autopass.autocard.repository.remote.passenger.services

import com.autopass.autocard.repository.remote.passenger.responses.CardDataResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface PassengerServices {

    @GET("card-data/document/{documentNumber}")
    fun getCardData(
        @Path("documentNumber") documentNumber: String
    ): Single<Response<List<CardDataResponse>>>

    @PATCH("card-data/{cardId}")
    fun cardConfirmation(
        @Path("cardId") cardId: String
    ): Observable<Response<Any>>
}