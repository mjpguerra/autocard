package com.autopass.autocard.presentation.card.actions

import com.autopass.autocard.core.exceptions.DueException
import com.autopass.autocard.core.nfc.cardblocks.StatusBlock
import org.joda.time.DateTimeZone
import org.joda.time.LocalDate

class CheckCardDue {

    fun execute(statusBlock: StatusBlock) {
        val today = LocalDate.now(DateTimeZone.forID("America/Sao_Paulo"))
        if (statusBlock.expirationDate.isBefore(today)) throw DueException()
    }
}