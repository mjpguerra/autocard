package com.autopass.autocard.di


import com.autopass.autocard.presentation.answers.AnswerContract
import com.autopass.autocard.presentation.answers.AnswerPresenter
import com.autopass.autocard.presentation.answers.actions.Wait
import com.autopass.autocard.presentation.card.CardContract
import com.autopass.autocard.presentation.card.CardPresenter
import com.autopass.autocard.presentation.card.OperationDialog
import com.autopass.autocard.presentation.card.actions.*
import com.autopass.autocard.presentation.register.RegisterContract
import com.autopass.autocard.presentation.register.RegisterPresenter
import com.autopass.autocard.presentation.register.actions.*
import org.koin.dsl.module

object PresenterModule {

    val modules = module {

        /** Common Action */
        factory { Wait() }

        /** Register */
        factory { (contract: RegisterContract) -> RegisterPresenter(contract, get()) }

        /** Card */
        factory { (contract: CardContract) -> CardPresenter(contract, get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get()) }

        /** Answer */
        factory { (contract: AnswerContract) -> AnswerPresenter(contract, get()) }

        /** Card Data Passenger */
        factory { SaveMediaNumber(get())}
        factory { SaveOperationId(get())}
        factory { SavePassengerId(get())}
        factory { SaveDocumentNumber(get())}
        factory { GetDocumentNumber(get())}
        factory { SecondaryInfoSR(get())}
        factory { MainInfoSR(get())}
        factory { GetCardDataPassenger(get(),get(),get(),get())}


        factory { StartDevice(get()) }
        factory { CreateConnection(get()) }
        factory { WaitCardRemove(get()) }
        factory { IssueDataSR(get()) }
        factory { StatusSR(get()) }
        factory { CheckCardDue() }
        factory { CheckCardStatus() }
        factory { ConfirmationCard(get()) }
        factory { AppSR(get()) }
        factory { OperationDialog() }


    }
}