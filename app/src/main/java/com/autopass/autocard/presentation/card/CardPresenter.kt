package com.autopass.autocard.presentation.card

import android.util.Log
import com.autopass.autocard.R
import com.autopass.autocard.core.common.BasePresenter
import com.autopass.autocard.core.exceptions.*
import com.autopass.autocard.presentation.card.actions.*
import com.autopass.autocard.presentation.register.actions.GetCardDataPassenger
import com.autopass.autocard.presentation.register.actions.GetDocumentNumber
import com.autopass.autocard.repository.remote.passenger.responses.CardDataResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.joda.time.DateTimeZone
import org.joda.time.LocalDate
import retrofit2.Response

class CardPresenter(private val contract: CardContract,
                    private val startDevice: StartDevice,
                    private val createConnection: CreateConnection,
                    private val waitCardRemove: WaitCardRemove,
                    private val statusSR: StatusSR,
                    private val issueDataSR: IssueDataSR,
                    private val mainInfoSR: MainInfoSR,
                    private val secondaryInfoSR: SecondaryInfoSR,
                    private val getCardDataPassenger: GetCardDataPassenger,
                    private val getDocumentNumber: GetDocumentNumber,
                    private val appSR: AppSR,
                    private val confirmationCard : ConfirmationCard
) : BasePresenter() {

     private var listcardDataResponse : List<CardDataResponse>? = null

    fun onCreate(){
        getDocumentNumber.execute()
            .doOnSubscribe { loading = true }
            .map {
                cardDataPassenger(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe().also { addDisposable(it) }
    }

    fun cardDataPassenger(number: String) {
        getCardDataPassenger.execute(number)
            .map {
                Log.i(AUTOCARD_ERRORS, it.body()!!.toString())
                listcardDataResponse = it.body()!!
                contract.navigateToAnswer(R.string.completed_dots, "Usuário localizado")

            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    loading = false
                    if(listcardDataResponse!!.size > 1) {
                        val dialogFragment = OperationDialog.newInstance(listcardDataResponse!!)
                        contract.openDialogOperation(dialogFragment)
                    }else{
                        startCardSaving(listcardDataResponse!![0])
                    }
                },
                {
                    loading = false
                    contract.navigateToAnswerErro(
                        R.string.error,
                        "Usuário não localizado"
                    )
                    Log.e(AUTOCARD_ERRORS, "TODO: Error Handler - RegisterPresenter")
                }
            ).also { addDisposable(it) }
    }

    fun startCardSaving(cardDataResponse : CardDataResponse) {
        startDevice.execute()
            .subscribeOn(Schedulers.io())
            .map { createConnection.execute(it) }
            .map { loading = true
                statusSR.save(cardDataResponse!!.cardStatus.status.toString(),
                cardDataResponse!!.cardStatus.expirationDate,
                "0") }
            .map { if(it < 0) throw CardStatusException() else it }
            .map { issueDataSR.save(cardDataResponse!!.cardIssueData.issuerId.toString(),
                cardDataResponse!!.cardIssueData.emissionDate,
                cardDataResponse!!.cardIssueData.cardType.toString(),
                cardDataResponse!!.cardIssueData.cardSeq,
                cardDataResponse!!.cardIssueData.mapVersion.toString(),
                cardDataResponse!!.cardIssueData.operationId.toString(),
                "0") }
            .map { if(it < 0) throw CardDataException() else it }
            .map {
                mainInfoSR.save(
                    cardDataResponse!!.apps[0].creditType.toString(),
                    cardDataResponse!!.apps[0].productId    .toString(),
                    cardDataResponse!!.apps[0].appVersion.toString(),
                LocalDate.now(DateTimeZone.forID("America/Sao_Paulo")).toString(),
                    cardDataResponse!!.apps[0].appStatus.toString(),
                    cardDataResponse!!.apps[0].expirationAppDate,
                LocalDate.now(DateTimeZone.forID("America/Sao_Paulo")).toString(),
                "0",
                "0",
                "0"
                )}
            .map { if(it < 0) throw CardMainInfoException() else it }
            .map {
                if (cardDataResponse!!.apps.size > 1) {
                    secondaryInfoSR.save(
                        cardDataResponse!!.apps[1].creditType.toString(),
                        cardDataResponse!!.apps[1].productId.toString(),
                        cardDataResponse!!.apps[1].appVersion.toString(),
                        LocalDate.now(DateTimeZone.forID("America/Sao_Paulo")).toString(),
                        cardDataResponse!!.apps[1].appStatus.toString(),
                        cardDataResponse!!.apps[1].expirationAppDate,
                        LocalDate.now(DateTimeZone.forID("America/Sao_Paulo")).toString(),
                        "0",
                        "0",
                        "0"
                    )
                }else{
                    secondaryInfoSR.save(
                        "0",
                        "0",
                        "0",
                        LocalDate.now(DateTimeZone.forID("America/Sao_Paulo")).toString(),
                        "0",
                        LocalDate.now(DateTimeZone.forID("America/Sao_Paulo")).toString(),
                        LocalDate.now(DateTimeZone.forID("America/Sao_Paulo")).toString(),
                        "0",
                        "0",
                        "0"
                    )
                }
            }
            .map { if(it < 0) throw CardSecundaryInfoException() else it }
            .map {
                appSR.save(
                    mainWalletBalance = "0",
                    mainWalletCounter = "0",
                    mainCounter = "0",
                    secondaryWalletBalance = "0",
                    secondaryWalletCounter = "0",
                    secondaryCounter = "0"
                )
            }
            .map { if(it < 0) throw AppSRException() else it }
            .flatMap { confirmationCard.execute(cardDataResponse!!.cardIssueData.cardId) }
            .map {
                if(it.code() != 202)
                    throw CardConfirmationException()

                contract.goTo(R.string.completed_dots, "Cartão impresso com sucesso")
                loading = false}

           //  }
            .map { waitCardRemove.execute() }
            .onErrorReturn {
                contract.navigateToAnswerErro(R.string.error, "Erro ao imprimir o cartão,\n refaça a transação")
                loading = false
                Log.e(AUTOCARD_ERRORS, it.message)
            }
            .repeat()
            .retry()
            .subscribe(
                { Log.d(AUTOCARD_ERRORS, "Successfully save card") },
                {
                    loading = false
                    contract.navigateToAnswerErro(R.string.error, "Erro ao imprimir o cartão,\n refaça a transação")
                    Log.e(AUTOCARD_ERRORS, "Error: CardPresenter -> card saving") }
            ).also { addDisposable(it) }
    }
}