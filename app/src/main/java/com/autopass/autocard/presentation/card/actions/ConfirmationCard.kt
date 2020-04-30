package com.autopass.autocard.presentation.card.actions

import com.autopass.autocard.repository.Repository
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

class ConfirmationCard(private val repository: Repository) {

    fun execute(cardId: String): Observable<Response<Any>>? {
        return repository.remote.passenger.cardConfirmation(cardId)
    }
}