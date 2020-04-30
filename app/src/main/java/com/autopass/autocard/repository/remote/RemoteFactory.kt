package com.autopass.autocard.repository.remote

import com.autopass.autocard.repository.remote.passenger.resources.RemotePassengerResources

interface RemoteFactory {
    val passenger: RemotePassengerResources
}