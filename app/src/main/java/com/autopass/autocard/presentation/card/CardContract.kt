package com.autopass.autocard.presentation.card

import android.content.Context

interface CardContract {
    fun context(): Context
    fun goTo(title: Int, message: String)
    fun backTo()
}