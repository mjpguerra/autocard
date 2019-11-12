package com.autopass.autocard.presentation.register

import android.content.Context

interface RegisterContract {

    fun navigateToAnswer(title: Int, message: String)
    fun context(): Context
}