package com.autopass.autocard.presentation.card.enums

import com.autopass.autocard.core.exceptions.*
import com.autopass.autocard.core.nfc.cardblocks.IssueDataBlock


class CheckType {

    fun execute(issueDataBlock: IssueDataBlock): IssueDataBlock {
        return when (issueDataBlock.cardType) {
            CardType.MASTER.id -> throw MasterException()
            CardType.DRIVER.id -> throw DriverException()
            CardType.TICKET_COLLECTOR.id -> throw TicketCollectorException()
            CardType.PASSENGER.id -> issueDataBlock
            CardType.RELEASED_BY_MONEY.id -> throw ReleasedByMoneyException()
            else -> throw CardReadException()
        }
    }
}