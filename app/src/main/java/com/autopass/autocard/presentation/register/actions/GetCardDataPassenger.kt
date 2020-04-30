package com.autopass.autocard.presentation.register.actions

import com.autopass.autocard.repository.Repository
import com.autopass.autocard.repository.remote.passenger.responses.CardDataResponse
import io.reactivex.Single
import retrofit2.Response

class GetCardDataPassenger(
    private val repository: Repository,
    private val saveMediaNumber: SaveMediaNumber,
    private val saveOperationId: SaveOperationId,
    private val savePassengerId: SavePassengerId
) {

    fun execute(documentNumber: String): Single<Response<List<CardDataResponse>>> {
        return repository.remote.passenger.getCardData(documentNumber)
    }
}