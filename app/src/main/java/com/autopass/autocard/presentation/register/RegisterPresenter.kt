package com.autopass.autocard.presentation.register

import com.autopass.autocard.core.common.BasePresenter
import com.autopass.autocard.core.common.keyboards.KeyboardController

class RegisterPresenter(private val contract: RegisterContract) : BasePresenter() {

    val keyboard = KeyboardController(11)

}