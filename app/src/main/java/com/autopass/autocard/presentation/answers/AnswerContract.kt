package com.autopass.autocard.presentation.answers

import androidx.annotation.StyleRes

interface AnswerContract {
    fun setTheme(@StyleRes style: Int)
    fun goTo()
    fun backTo()
    fun setBindingAndIcon(icon: Int)
}