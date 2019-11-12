package com.autopass.autocard.di


import com.autopass.autocard.presentation.answers.AnswerContract
import com.autopass.autocard.presentation.answers.AnswerPresenter
import com.autopass.autocard.presentation.answers.actions.Wait
import com.autopass.autocard.presentation.card.CardContract
import com.autopass.autocard.presentation.card.CardPresenter
import com.autopass.autocard.presentation.register.RegisterContract
import com.autopass.autocard.presentation.register.RegisterPresenter
import org.koin.dsl.module

object PresenterModule {

    val modules = module {

        /** Common Action */
        factory { Wait() }

        /** Register */
        factory { (contract: RegisterContract) -> RegisterPresenter(contract) }

        /** Card */
        factory { (contract: CardContract) -> CardPresenter(contract, get()) }

        /** Answer */
        factory { (contract: AnswerContract) -> AnswerPresenter(contract, get()) }

    }
}