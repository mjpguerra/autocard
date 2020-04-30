package com.autopass.autocard.repository.di

import com.autopass.autocard.BuildConfig
import com.autopass.autocard.repository.Repository
import com.autopass.autocard.repository.remote.Api
import com.autopass.autocard.repository.remote.passenger.services.PassengerServices
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object RepositoryModule {

    val modules = module {

        /** Repository */
        single { Repository(androidApplication()) }

        /** Passenger Services */
        factory {
            Api<PassengerServices>().create(
                PassengerServices::class.java,
                BuildConfig.THEMOVIE_URL
            )
        }

    }
}