package com.autopass.autocard.presentation.register

import android.util.Log
import com.autopass.autocard.R
import com.autopass.autocard.core.common.BasePresenter
import com.autopass.autocard.core.common.keyboards.KeyboardController
import com.autopass.autocard.presentation.register.actions.GetCardDataPassenger
import com.autopass.autocard.presentation.register.actions.SaveDocumentNumber
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegisterPresenter(
    private val contract: RegisterContract,
    private val saveDocumentNumber : SaveDocumentNumber
) : BasePresenter() {

    val keyboard = KeyboardController(11)

    fun passengerCardData(){
        saveDocumentNumber.execute(contract.documentNumber())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { contract.navigateToCard()},
                {
                    Log.e(AUTOCARD_ERRORS, "TODO: Error Handler - RegisterPresenter") }
            ).also { addDisposable(it) }
    }

}