package com.autopass.autocard.presentation.card

import android.content.Context
import com.autopass.autocard.R

interface CardContract {
    fun context(): Context
    fun goTo(title: Int, message: String)
    fun backTo()
    fun navigateToAnswer(title: Int, message: String)
    fun navigateToAnswerErro(title: Int, message: String)
    fun openDialogOperation( operationDialog : OperationDialog)

}