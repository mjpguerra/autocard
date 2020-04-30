package com.autopass.autocard.repository.remote

import com.autopass.autocard.repository.remote.passenger.RemotePassengerRepository
import com.autopass.autocard.repository.remote.passenger.resources.RemotePassengerResources
import com.autopass.autocard.repository.remote.passenger.services.PassengerServices
import org.koin.core.KoinComponent
import org.koin.core.inject

class RemoteRepository : RemoteFactory, KoinComponent {

    private val passengerServices: PassengerServices by inject()

    override val passenger: RemotePassengerResources = RemotePassengerRepository(passengerServices)

}