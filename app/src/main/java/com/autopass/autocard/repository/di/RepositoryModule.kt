package com.autopass.autocard.repository.di

import com.autopass.autocard.repository.Repository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object RepositoryModule {

    val modules = module {
        single { Repository(androidApplication()) }

    }
}