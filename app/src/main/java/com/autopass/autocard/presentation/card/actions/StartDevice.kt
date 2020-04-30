package com.autopass.autocard.presentation.card.actions


import com.autopass.autocard.core.nfc.NfcController
import io.reactivex.Observable

class StartDevice(private val nfcController: NfcController) {

    fun execute(): Observable<ByteArray> {
        return Observable.fromCallable { nfcController.serialDevice.open() }
            .flatMap { nfcController.serialDevice.startReading() }
    }
}