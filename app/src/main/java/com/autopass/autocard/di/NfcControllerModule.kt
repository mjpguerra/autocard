package com.autopass.autocard.di

import br.inf.planeta.Reader
import com.autopass.autocard.core.nfc.NfcController
import com.autopass.autocard.core.nfc.legacy.VL4MIF
import com.autopass.autocard.core.nfc.legacy.VSC_COMM
import com.autopass.autocard.core.nfc.managers.SerialDeviceManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object NfcControllerModule {
    val modules = module {
        /** NfcController */
        single { Reader(androidApplication().applicationContext) }
        single { VSC_COMM(androidApplication().applicationContext) }
        single { VL4MIF(get()) }
        single { SerialDeviceManager(androidApplication().applicationContext, get()) }
        single { NfcController(get(), get()) }
    }
}