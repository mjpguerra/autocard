package com.autopass.autocard.presentation.card.actions

import android.app.PendingIntent
import com.autopass.autocard.core.exceptions.BlocklistException
import com.autopass.autocard.core.exceptions.CardReadException
import com.autopass.autocard.core.exceptions.InactiveException
import com.autopass.autocard.core.nfc.cardblocks.StatusBlock
import com.autopass.autocard.presentation.card.enums.CardStatus

class CheckCardStatus {

    fun execute(statusBlock: StatusBlock): StatusBlock {
        return when (statusBlock.cardStatus) {
            CardStatus.ACTIVE.id -> statusBlock
            CardStatus.BLOCKED.id -> throw BlocklistException()
            CardStatus.CANCELED.id -> throw PendingIntent.CanceledException()
            CardStatus.INACTIVE.id -> throw InactiveException()
            else -> throw CardReadException()
        }
    }
}