package com.autopass.autocard.di


import com.autopass.autocard.presentation.register.RegisterPresenter
import com.autopass.autopay.presentation.register.token.RegisterContract
import org.koin.dsl.module

object PresenterModule {

    val modules = module {

        /** Register */
        factory { (contract: RegisterContract) -> RegisterPresenter(contract) }


    }
}