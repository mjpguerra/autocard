package com.autopass.autocard.presentation.register

import com.autopass.autocard.core.common.BasePresenter
import com.autopass.autocard.core.common.keyboards.KeyboardController
import com.autopass.autopay.presentation.register.token.RegisterContract

class RegisterPresenter(private val contract: RegisterContract) : BasePresenter() {

    val keyboard = KeyboardController(6)

}