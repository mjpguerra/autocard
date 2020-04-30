package com.autopass.autocard.core.nfc.legacy

import br.inf.planeta.Reader
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetCardHeader : KoinComponent {

    private val reader: Reader by inject()

    fun execute() = reader
}