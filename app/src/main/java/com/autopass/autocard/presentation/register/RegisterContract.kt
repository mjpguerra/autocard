package com.autopass.autocard.presentation.register

import android.content.Context

interface RegisterContract {

    fun navigateToCard()
    fun context(): Context
    fun documentNumber(): String
}